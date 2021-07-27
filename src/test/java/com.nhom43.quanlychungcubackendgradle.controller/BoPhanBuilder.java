package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class BoPhanBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static BoPhanDto getDto() {
        BoPhanDto dto = new BoPhanDto();
        dto.setId("1");
        return dto;
    }
}