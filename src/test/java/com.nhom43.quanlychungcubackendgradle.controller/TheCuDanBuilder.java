package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TheCuDanBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static TheCuDanDto getDto() {
        TheCuDanDto dto = new TheCuDanDto();
        dto.setId("1");
        return dto;
    }
}