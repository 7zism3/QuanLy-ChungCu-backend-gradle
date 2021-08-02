package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto extends AbstractDto<Long> {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;

}