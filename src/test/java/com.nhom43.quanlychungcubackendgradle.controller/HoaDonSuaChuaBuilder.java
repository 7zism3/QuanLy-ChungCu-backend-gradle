package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class HoaDonSuaChuaBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static HoaDonSuaChuaDto getDto() {
        HoaDonSuaChuaDto dto = new HoaDonSuaChuaDto();
        dto.setId("1");
        return dto;
    }
}