package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ThongBaoRiengBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ThongBaoRiengDto getDto() {
        ThongBaoRiengDto dto = new ThongBaoRiengDto();
        dto.setId("1");
        return dto;
    }
}