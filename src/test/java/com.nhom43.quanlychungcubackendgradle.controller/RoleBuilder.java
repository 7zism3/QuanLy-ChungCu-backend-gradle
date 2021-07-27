package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RoleBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static RoleDto getDto() {
        RoleDto dto = new RoleDto();
        dto.setId("1");
        return dto;
    }
}