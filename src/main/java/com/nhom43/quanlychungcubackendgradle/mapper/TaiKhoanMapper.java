package com.nhom43.quanlychungcubackendgradle.mapper;

import com.nhom43.quanlychungcubackendgradle.dto.TaiKhoanDto;
import com.nhom43.quanlychungcubackendgradle.entity.TaiKhoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaiKhoanMapper extends EntityMapper<TaiKhoanDto, TaiKhoan> {
}