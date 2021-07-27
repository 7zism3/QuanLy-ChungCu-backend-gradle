package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ChiTietHoaDonSuaChuaBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ChiTietHoaDonSuaChuaDto getDto() {
        ChiTietHoaDonSuaChuaDto dto = new ChiTietHoaDonSuaChuaDto();
        dto.setId("1");
        return dto;
    }
}