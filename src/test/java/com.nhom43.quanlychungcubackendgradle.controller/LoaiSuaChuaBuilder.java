package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LoaiSuaChuaBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static LoaiSuaChuaDto getDto() {
        LoaiSuaChuaDto dto = new LoaiSuaChuaDto();
        dto.setId("1");
        return dto;
    }
}