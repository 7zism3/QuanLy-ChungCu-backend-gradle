package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.ABCDto;
import com.nhom43.quanlychungcubackendgradle.dto.response.DashboardResponse;
import com.nhom43.quanlychungcubackendgradle.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/dashboard")
@RestController
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public ResponseEntity<?> thongKe() {
        DashboardResponse dashboardResponse = dashboardService.thongKe();
        return ResponseEntity.ok(dashboardResponse);
    }

//    @GetMapping("/user")
//    public ResponseEntity<?> thongKeUser() {
//        DashboardResponse dashboardResponse = dashboardService.thongKe();
//        return ResponseEntity.ok(dashboardResponse);
//    }

}