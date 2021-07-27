package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class DichVuCoDinhBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static DichVuCoDinhDto getDto() {
        DichVuCoDinhDto dto = new DichVuCoDinhDto();
        dto.setId("1");
        return dto;
    }
}