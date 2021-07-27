package com.nhom43.quanlychungcubackendgradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class UserBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static UserDto getDto() {
        UserDto dto = new UserDto();
        dto.setId("1");
        return dto;
    }
}