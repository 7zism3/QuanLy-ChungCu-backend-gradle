package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.CuDanDto;
import com.nhom43.quanlychungcubackendgradle.dto.response.CountHoaDonResponse;
import com.nhom43.quanlychungcubackendgradle.dto.response.DashboardResponse;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.mapper.CuDanMapper;
import com.nhom43.quanlychungcubackendgradle.repository.CuDanRepository;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonDichVuRepository;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonSuaChuaRepository;
import com.nhom43.quanlychungcubackendgradle.repository.UserRepository;
import com.nhom43.quanlychungcubackendgradle.share.model.NotificationEmail;
import com.nhom43.quanlychungcubackendgradle.share.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@AllArgsConstructor
@Service
@Transactional
public class DashboardService {

    private final CuDanRepository cuDanRepository;
    private final UserRepository userRepository;
    private final HoaDonDichVuRepository hoaDonDichVuRepository;
    private final HoaDonSuaChuaRepository hoaDonSuaChuaRepository;
    private final CuDanMapper cuDanMapper;
    private final MailService mailService;

    public DashboardResponse thongKe() {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setSoDanCuTuoiNhoHon18(cuDanRepository.countCuDanByNgaySinh18());
        dashboardResponse.setSoDanCuTuoiTu18Den60(cuDanRepository.countCuDanByNgaySinh1860());
        dashboardResponse.setSoDanCuTuoiLonHon60(cuDanRepository.countCuDanByNgaySinh60());

        dashboardResponse.setSoDanCuTuoiNhoHon18_CoSinhNhatTrongThang
                (cuDanRepository.countCuDanByTuoiNhoHon18_CoSinhNhatTrongThang());

        dashboardResponse.setSoHoaDonThangTruocChuaThanhToan
                (hoaDonDichVuRepository.countHoaDonDichVuThangTruocByTrangThai(false));
        dashboardResponse.setSoHoaDonThangTruocDaThanhToan
                (hoaDonDichVuRepository.countHoaDonDichVuThangTruocByTrangThai(true));


        dashboardResponse.setSoHoaDonSuaChuaThangTruocChuaThanhToan
                (hoaDonSuaChuaRepository.countHoaDonSuaChuaThangTruocByTrangThai(false));
        dashboardResponse.setSoHoaDonSuaChuaThangTruocDaThanhToan
                (hoaDonSuaChuaRepository.countHoaDonSuaChuaThangTruocByTrangThai(true));

        return dashboardResponse;
    }

    public List<CuDanDto> findAllBySinhNhatThangNay() {
        List<CuDan> cuDanList = cuDanRepository.findAllBySinhNhatThangNay();
        if (cuDanList.isEmpty())
            throw new ResourceNotFoundException("Kh??ng co?? c?? d??n na??o c?? sinh nh???t trong th??ng n??y");
        return cuDanMapper.toDto(cuDanList);
    }

    public void guiLoiChucMungSinhNhat(Long id) {
        CuDan cuDan = cuDanRepository.getById(id);
        String email = cuDan.getEmail();
        if (email == null || email.equals("")) return;
        mailService.sendMail(new NotificationEmail("Ch??c m???ng sinh nh???t b???n",
                cuDan.getEmail(),
                " ???????c bi???t b???n c?? sinh nh???t trong th??ng n??y.\n" +
                        "Ban qu???n l?? chung c?? g???i l???i ch??c m???ng sinh nh???t ?????n b???n.\n" +
                        "Ch??c b???n m???t sinh nh???t vui v??? b??n gia ????nh, ng?????i th??n!"));
    }

    public CountHoaDonResponse thongKeHoaDonDichVu(String nam, String thang) {
        if (thang.length() == 1) thang = "0" + thang;
        CountHoaDonResponse countHoaDonResponse = new CountHoaDonResponse();
        countHoaDonResponse.setSoHoaDonChuaThanhToan
                (hoaDonDichVuRepository.countHoaDonDichVuByTrangThaiAndNgayTao(false, nam, thang));
        countHoaDonResponse.setSoHoaDonDaThanhToan
                (hoaDonDichVuRepository.countHoaDonDichVuByTrangThaiAndNgayTao(true, nam, thang));
        return countHoaDonResponse;
    }

    public CountHoaDonResponse thongKeHoaDonSuaChua(String nam, String thang) {
        if (thang.length() == 1) thang = "0" + thang;
        CountHoaDonResponse countHoaDonResponse = new CountHoaDonResponse();
        countHoaDonResponse.setSoHoaDonChuaThanhToan
                (hoaDonSuaChuaRepository.countHoaDonSuaChuaByTrangThaiAndNgayTao(false, nam, thang));
        countHoaDonResponse.setSoHoaDonDaThanhToan
                (hoaDonSuaChuaRepository.countHoaDonSuaChuaByTrangThaiAndNgayTao(true, nam, thang));
        return countHoaDonResponse;
    }
}