package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PhuongTienBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static PhuongTienDto getDto() {
        PhuongTienDto dto = new PhuongTienDto();
        dto.setId("1");
        return dto;
    }
}