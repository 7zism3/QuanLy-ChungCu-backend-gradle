package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.ERole;

public class RoleDto extends AbstractDto<Integer> {
    
    private Integer id;
    
    private ERole name;

    public RoleDto() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public ERole getName() {
        return this.name;
    }
}