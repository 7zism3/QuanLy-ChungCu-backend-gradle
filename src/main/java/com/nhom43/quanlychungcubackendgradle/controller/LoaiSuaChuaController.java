package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.LoaiSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.LoaiSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.service.LoaiSuaChuaService;
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

@RequestMapping("/loai-sua-chua")
@RestController
public class LoaiSuaChuaController {
    private final LoaiSuaChuaService loaiSuaChuaService;

    public LoaiSuaChuaController(LoaiSuaChuaService loaiSuaChuaService) {
        this.loaiSuaChuaService = loaiSuaChuaService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated LoaiSuaChuaDto loaiSuaChuaDto) {
        loaiSuaChuaService.save(loaiSuaChuaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoaiSuaChuaDto> findById(@PathVariable("id") Long id) {
        LoaiSuaChuaDto loaiSuaChua = loaiSuaChuaService.findById(id);
        return ResponseEntity.ok(loaiSuaChua);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        loaiSuaChuaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<LoaiSuaChuaDto>> pageQuery(LoaiSuaChuaDto loaiSuaChuaDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<LoaiSuaChuaDto> loaiSuaChuaPage = loaiSuaChuaService.findByCondition(loaiSuaChuaDto, pageable);
        return ResponseEntity.ok(loaiSuaChuaPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated LoaiSuaChuaDto loaiSuaChuaDto, @PathVariable("id") Long id) {
        loaiSuaChuaService.update(loaiSuaChuaDto, id);
        return ResponseEntity.ok().build();
    }
}