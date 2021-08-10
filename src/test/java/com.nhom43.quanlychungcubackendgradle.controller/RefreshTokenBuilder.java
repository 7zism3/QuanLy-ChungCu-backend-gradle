package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RefreshTokenBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static RefreshTokenDto getDto() {
        RefreshTokenDto dto = new RefreshTokenDto();
        dto.setId("1");
        return dto;
    }
}