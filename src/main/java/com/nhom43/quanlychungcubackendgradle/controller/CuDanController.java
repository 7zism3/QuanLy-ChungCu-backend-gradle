package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.CuDanDto;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.mapper.CuDanMapper;
import com.nhom43.quanlychungcubackendgradle.service.CuDanService;
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

@RequestMapping("/cu-dan")
@RestController
public class CuDanController {
    private final CuDanService cuDanService;

    public CuDanController(CuDanService cuDanService) {
        this.cuDanService = cuDanService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated CuDanDto cuDanDto) {
        cuDanService.save(cuDanDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuDanDto> findById(@PathVariable("id") Long id) {
        CuDanDto cuDan = cuDanService.findById(id);
        return ResponseEntity.ok(cuDan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        cuDanService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<CuDanDto>> pageQuery(CuDanDto cuDanDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CuDanDto> cuDanPage = cuDanService.findByCondition(cuDanDto, pageable);
        return ResponseEntity.ok(cuDanPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CuDanDto cuDanDto, @PathVariable("id") Long id) {
        cuDanService.update(cuDanDto, id);
        return ResponseEntity.ok().build();
    }
}