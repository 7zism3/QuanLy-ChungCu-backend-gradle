package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class _BoPhanBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static _BoPhanDto getDto() {
        _BoPhanDto dto = new _BoPhanDto();
        dto.setId("1");
        return dto;
    }
}