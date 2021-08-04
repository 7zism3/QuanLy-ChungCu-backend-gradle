package com.nhom43.quanlychungcubackendgradle.admin.server;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import com.nhom43.quanlychungcubackendgradle.entity.ChiTietHoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.entity.DichVuCoDinh;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ScheduledService {

    //    private final IBookingRepository iBookingRepository;
//    private final IVerificationTokenAccountRepository iVerificationTokenAccountRepository;
//    private final IVerificationTokenPasswordRepository iVerificationTokenPasswordRepository;
    private final CanHoRepository canHoRepository;
    private final HoaDonDichVuRepository hoaDonDichVuRepository;
    private final DichVuCoDinhRepository dichVuCoDinhRepository;
    private final ChiTietHoaDonDichVuRepository chiTietHoaDonDichVuRepository;
    private final PhuongTienRepository phuongTienRepository;


//    @Scheduled(fixedDelay = 43200000)  //6 tiếng lọc một lần
//    public void TokenCleaning() {
//        try {
//            Instant time = Instant.now();
//            System.out.println("Lọc token password " + time);
//            List<VerificationTokenPassword> tokenPasswordList = iVerificationTokenPasswordRepository.findAll();
//            if (tokenPasswordList.size() != 0) {
//                for (VerificationTokenPassword verificationTokenPassword : tokenPasswordList) {
//                    if (time.isAfter(verificationTokenPassword.getExpiryDate())) {
//                        iVerificationTokenPasswordRepository.deleteById(verificationTokenPassword.getId());
//                    }
//                }
//            }
//            List<VerificationTokenAccount> tokenAccountList = iVerificationTokenAccountRepository.findAll();
//            if (tokenAccountList.size() != 0) {
//                for (VerificationTokenAccount verificationTokenAccount : tokenAccountList) {
//                    if (time.isAfter(verificationTokenAccount.getExpiryDate())) {
//                        iVerificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
//                        iUserRepository.deleteById(verificationTokenAccount.getUser().getId());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Lọc token bị lỗi " + e);
//        }
//    }

    //    @Scheduled(cron = "0 15 10 L * ?") // 1 tháng 1 lần vào ngày cuối cuàng của tháng
    // Test time hiện tại @Scheduled(cron = "0 m h * * ?")
    @Scheduled(cron = "0 32 17 * * ?")
    public void TaoHoaDonHangThang() {
        try {
            Instant time = Instant.now();
            System.out.println("-- Tạo hóa đơn cố định " + time);
            // Lấy ra danh sách các căn hộ đã có ng sinh sống
            List<CanHo> canHoList = canHoRepository.findAllByTrangThai(true);
            for (CanHo canHo : canHoList) {
                // Tạo hóa đơn
                HoaDonDichVu hoaDonDichVu = new HoaDonDichVu(null, time, false, canHo);
                hoaDonDichVuRepository.save(hoaDonDichVu);
                // Thêm hóa đơn chi tiết phí dịch vụ bảo trì
                DichVuCoDinh dvBaoTri = dichVuCoDinhRepository.findFirstByTen("Bao tri");
                ChiTietHoaDonDichVu ctBaoTri =
                        new ChiTietHoaDonDichVu(null, dvBaoTri.getDonGia(), canHo.getDienTich(), dvBaoTri, hoaDonDichVu);
                chiTietHoaDonDichVuRepository.save(ctBaoTri);
                // Thêm hóa đơn chi tiết gửi xe
                int soLuongXeOto = phuongTienRepository.countPhuongTienByCanHoAndLoaiXeAndDaXoa(canHo, "O to", false);
                if (soLuongXeOto > 0){
                    DichVuCoDinh dvGuiXeOto = dichVuCoDinhRepository.findFirstByTen("Gui xe Oto");
                    ChiTietHoaDonDichVu ctGuiXeOto =
                            new ChiTietHoaDonDichVu(null, dvGuiXeOto.getDonGia(), soLuongXeOto, dvGuiXeOto, hoaDonDichVu);
                    chiTietHoaDonDichVuRepository.save(ctGuiXeOto);
                }
                int soLuongXeMay = phuongTienRepository.countPhuongTienByCanHoAndLoaiXeAndDaXoa(canHo, "May", false);
                if (soLuongXeMay > 0){
                    DichVuCoDinh dvGuiXeMay = dichVuCoDinhRepository.findFirstByTen("Gui xe May");
                    ChiTietHoaDonDichVu ctGuiXeMay =
                            new ChiTietHoaDonDichVu(null, dvGuiXeMay.getDonGia(), soLuongXeMay, dvGuiXeMay, hoaDonDichVu);
                    chiTietHoaDonDichVuRepository.save(ctGuiXeMay);
                }
                int soLuongXeMayDien = phuongTienRepository.countPhuongTienByCanHoAndLoaiXeAndDaXoa(canHo, "May dien", false);
                if (soLuongXeMayDien > 0){
                    DichVuCoDinh dvGuiXeMayDien = dichVuCoDinhRepository.findFirstByTen("Gui xe May dien");
                    ChiTietHoaDonDichVu ctGuiXeMayDien =
                            new ChiTietHoaDonDichVu(null, dvGuiXeMayDien.getDonGia(), soLuongXeMayDien, dvGuiXeMayDien, hoaDonDichVu);
                    chiTietHoaDonDichVuRepository.save(ctGuiXeMayDien);
                }
                int soLuongXeDap = phuongTienRepository.countPhuongTienByCanHoAndLoaiXeAndDaXoa(canHo, "Dap", false);
                if (soLuongXeDap > 0){
                    DichVuCoDinh dvGuiXeDap = dichVuCoDinhRepository.findFirstByTen("Gui xe Dap");
                    ChiTietHoaDonDichVu ctGuiXeDap =
                            new ChiTietHoaDonDichVu(null, dvGuiXeDap.getDonGia(), soLuongXeDap, dvGuiXeDap, hoaDonDichVu);
                    chiTietHoaDonDichVuRepository.save(ctGuiXeDap);
                }

            }
        } catch (Exception e) {
            System.out.println("Tạo hóa đơn cố định bị lỗi: " + e);
        }
    }
}
