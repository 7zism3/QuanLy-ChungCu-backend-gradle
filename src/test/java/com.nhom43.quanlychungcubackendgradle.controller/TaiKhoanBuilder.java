package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class TaiKhoanBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static TaiKhoanDto getDto() {
        TaiKhoanDto dto = new TaiKhoanDto();
        dto.setId("1");
        return dto;
    }
}