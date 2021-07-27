package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class HoaDonDichVuBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static HoaDonDichVuDto getDto() {
        HoaDonDichVuDto dto = new HoaDonDichVuDto();
        dto.setId("1");
        return dto;
    }
}