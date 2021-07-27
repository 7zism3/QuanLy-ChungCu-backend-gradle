package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.PhuongTienDto;
import com.nhom43.quanlychungcubackendgradle.entity.PhuongTien;
import com.nhom43.quanlychungcubackendgradle.mapper.PhuongTienMapper;
import com.nhom43.quanlychungcubackendgradle.service.PhuongTienService;
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

@RequestMapping("/phuong-tien")
@RestController
public class PhuongTienController {
    private final PhuongTienService phuongTienService;

    public PhuongTienController(PhuongTienService phuongTienService) {
        this.phuongTienService = phuongTienService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated PhuongTienDto phuongTienDto) {
        phuongTienService.save(phuongTienDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhuongTienDto> findById(@PathVariable("id") Long id) {
        PhuongTienDto phuongTien = phuongTienService.findById(id);
        return ResponseEntity.ok(phuongTien);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        phuongTienService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<PhuongTienDto>> pageQuery(PhuongTienDto phuongTienDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PhuongTienDto> phuongTienPage = phuongTienService.findByCondition(phuongTienDto, pageable);
        return ResponseEntity.ok(phuongTienPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated PhuongTienDto phuongTienDto, @PathVariable("id") Long id) {
        phuongTienService.update(phuongTienDto, id);
        return ResponseEntity.ok().build();
    }
}