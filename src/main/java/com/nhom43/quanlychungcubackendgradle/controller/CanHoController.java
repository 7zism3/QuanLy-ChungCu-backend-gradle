package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.CanHoDto;
import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import com.nhom43.quanlychungcubackendgradle.mapper.CanHoMapper;
import com.nhom43.quanlychungcubackendgradle.service.CanHoService;
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

@RequestMapping("/can-ho")
@RestController
public class CanHoController {
    private final CanHoService canHoService;

    public CanHoController(CanHoService canHoService) {
        this.canHoService = canHoService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated CanHoDto canHoDto) {
        canHoService.save(canHoDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanHoDto> findById(@PathVariable("id") Long id) {
        CanHoDto canHo = canHoService.findById(id);
        return ResponseEntity.ok(canHo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        canHoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<CanHoDto>> pageQuery(CanHoDto canHoDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CanHoDto> canHoPage = canHoService.findByCondition(canHoDto, pageable);
        return ResponseEntity.ok(canHoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated CanHoDto canHoDto, @PathVariable("id") Long id) {
        canHoService.update(canHoDto, id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------------------------------------------------------------------------- //


    @GetMapping("")
    public ResponseEntity<List<CanHoDto>> findAll() {
        List<CanHoDto> canHoList = canHoService.findAll();
        return ResponseEntity.ok(canHoList);
    }

    @GetMapping("/hoat-dong")
    public ResponseEntity<List<CanHoDto>> findAllByTrangThaiHoatDong() {
        List<CanHoDto> canHoList = canHoService.findAllByTrangThai(true);
        return  ResponseEntity.ok(canHoList);
    }

    @GetMapping("/khong-hoat-dong")
    public ResponseEntity<List<CanHoDto>> findAllByTrangThaiKhongHoatDong() {
        List<CanHoDto> canHoList = canHoService.findAllByTrangThai(false);
        return  ResponseEntity.ok(canHoList);
    }

}