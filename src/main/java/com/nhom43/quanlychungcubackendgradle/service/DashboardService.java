package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.response.DashboardResponse;
import com.nhom43.quanlychungcubackendgradle.repository.CuDanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
@Transactional
public class DashboardService {

    private final CuDanRepository cuDanRepository;

    public DashboardResponse thongKe() {
        DashboardResponse dashboardResponse = new DashboardResponse();
        dashboardResponse.setSoDanCuTuoiNhoHon18(cuDanRepository.countCuDanByNgaySinh18());
        dashboardResponse.setSoDanCuTuoiTu18Den60(cuDanRepository.countCuDanByNgaySinh1860());
        dashboardResponse.setSoDanCuTuoiLonHon60(cuDanRepository.countCuDanByNgaySinh60());


        return dashboardResponse;
    }
}