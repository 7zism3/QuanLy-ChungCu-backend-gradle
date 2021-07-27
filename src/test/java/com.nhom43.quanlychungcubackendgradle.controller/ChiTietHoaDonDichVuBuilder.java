package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ChiTietHoaDonDichVuBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ChiTietHoaDonDichVuDto getDto() {
        ChiTietHoaDonDichVuDto dto = new ChiTietHoaDonDichVuDto();
        dto.setId("1");
        return dto;
    }
}