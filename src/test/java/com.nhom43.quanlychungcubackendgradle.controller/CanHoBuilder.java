package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class CanHoBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static CanHoDto getDto() {
        CanHoDto dto = new CanHoDto();
        dto.setId("1");
        return dto;
    }
}