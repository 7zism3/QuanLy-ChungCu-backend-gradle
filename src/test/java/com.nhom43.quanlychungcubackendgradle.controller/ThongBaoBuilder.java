package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ThongBaoBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ThongBaoDto getDto() {
        ThongBaoDto dto = new ThongBaoDto();
        dto.setId("1");
        return dto;
    }
}