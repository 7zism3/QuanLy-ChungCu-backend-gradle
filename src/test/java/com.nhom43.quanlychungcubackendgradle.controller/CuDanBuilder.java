package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CuDanBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static CuDanDto getDto() {
        CuDanDto dto = new CuDanDto();
        dto.setId("1");
        return dto;
    }
}