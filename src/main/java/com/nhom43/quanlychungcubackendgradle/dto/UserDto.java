package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.Role;

import java.util.Set;

public class UserDto extends AbstractDto<Long> {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

    public UserDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRoles(java.util.Set<com.nhom43.quanlychungcubackendgradle.entity.Role> roles) {
        this.roles = roles;
    }

    public java.util.Set<com.nhom43.quanlychungcubackendgradle.entity.Role> getRoles() {
        return this.roles;
    }
}