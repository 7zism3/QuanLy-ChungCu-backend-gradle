package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class _CanHoBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static _CanHoDto getDto() {
        _CanHoDto dto = new _CanHoDto();
        dto.setId("1");
        return dto;
    }
}