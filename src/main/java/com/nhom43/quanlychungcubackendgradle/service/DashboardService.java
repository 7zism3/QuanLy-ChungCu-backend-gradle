package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.CuDanDto;
import com.nhom43.quanlychungcubackendgradle.dto.response.CountHoaDonResponse;
import com.nhom43.quanlychungcubackendgradle.dto.response.DashboardResponse;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.mapper.CuDanMapper;
import com.nhom43.quanlychungcubackendgradle.repository.CuDanRepository;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonDichVuRepository;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonSuaChuaRepository;
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
    private final HoaDonDichVuRepository hoaDonDichVuRepository;
    private final HoaDonSuaChuaRepository hoaDonSuaChuaRepository;
    private final CuDanMapper cuDanMapper;

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
            throw new ResourceNotFoundException("Không có cư dân nào có sinh nhật trong tháng này");
        return cuDanMapper.toDto(cuDanList);
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