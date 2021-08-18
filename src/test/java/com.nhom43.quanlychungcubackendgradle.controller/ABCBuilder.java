package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ABCBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ABCDto getDto() {
        ABCDto dto = new ABCDto();
        dto.setId("1");
        return dto;
    }
}