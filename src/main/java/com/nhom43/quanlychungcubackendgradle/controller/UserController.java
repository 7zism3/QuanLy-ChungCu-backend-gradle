package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.CanHoDto;
import com.nhom43.quanlychungcubackendgradle.dto.CuDanDto;
import com.nhom43.quanlychungcubackendgradle.dto.UserDto;
import com.nhom43.quanlychungcubackendgradle.service.CanHoService;
import com.nhom43.quanlychungcubackendgradle.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;
    private final CanHoService canHoService;

    public UserController(UserService userService, CanHoService canHoService) {
        this.canHoService = canHoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<UserDto>> pageQuery(UserDto userDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> userPage = userService.findByCondition(userDto, pageable);
        return ResponseEntity.ok(userPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated UserDto userDto, @PathVariable("id") Long id) {
        userService.update(userDto, id);
        return ResponseEntity.ok().build();
    }


    // ------------------------------------------------------------------------------------------------------------- //


//    @GetMapping("/{id}/can-ho")
//    public ResponseEntity<?> getCanHoByUser_Username(@PathVariable("id") String username) {
//        CanHoDto canHo = canHoService.getCanHoByUser_Username(username);
//        return ResponseEntity.ok(canHo);
//    }

}