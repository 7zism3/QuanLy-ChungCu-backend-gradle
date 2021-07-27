package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class NhanVienBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static NhanVienDto getDto() {
        NhanVienDto dto = new NhanVienDto();
        dto.setId("1");
        return dto;
    }
}