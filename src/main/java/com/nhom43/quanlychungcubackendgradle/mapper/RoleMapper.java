package com.nhom43.quanlychungcubackendgradle.mapper;

import com.nhom43.quanlychungcubackendgradle.dto.RoleDto;
import com.nhom43.quanlychungcubackendgradle.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
}