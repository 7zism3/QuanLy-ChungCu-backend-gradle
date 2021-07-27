package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.TaiKhoanDto;
import com.nhom43.quanlychungcubackendgradle.entity.TaiKhoan;
import com.nhom43.quanlychungcubackendgradle.mapper.TaiKhoanMapper;
import com.nhom43.quanlychungcubackendgradle.service.TaiKhoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/tai-khoan")
@RestController
public class TaiKhoanController {
    private final TaiKhoanService taiKhoanService;

    public TaiKhoanController(TaiKhoanService taiKhoanService) {
        this.taiKhoanService = taiKhoanService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated TaiKhoanDto taiKhoanDto) {
        taiKhoanService.save(taiKhoanDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoanDto> findById(@PathVariable("id") Long id) {
        TaiKhoanDto taiKhoan = taiKhoanService.findById(id);
        return ResponseEntity.ok(taiKhoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        taiKhoanService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<TaiKhoanDto>> pageQuery(TaiKhoanDto taiKhoanDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TaiKhoanDto> taiKhoanPage = taiKhoanService.findByCondition(taiKhoanDto, pageable);
        return ResponseEntity.ok(taiKhoanPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated TaiKhoanDto taiKhoanDto, @PathVariable("id") Long id) {
        taiKhoanService.update(taiKhoanDto, id);
        return ResponseEntity.ok().build();
    }
}