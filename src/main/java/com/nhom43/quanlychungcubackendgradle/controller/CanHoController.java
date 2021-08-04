package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.*;
import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.mapper.CanHoMapper;
import com.nhom43.quanlychungcubackendgradle.service.*;
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
    private final PhuongTienService phuongTienService;
    private final TheCuDanService theCuDanService;
    private final CuDanService cuDanService;
    private final HoaDonDichVuService hoaDonDichVuService;

    public CanHoController(CanHoService canHoService
            , PhuongTienService phuongTienService
            , TheCuDanService theCuDanService
            , CuDanService cuDanService
            , HoaDonDichVuService hoaDonDichVuService
    ) {
        this.canHoService = canHoService;
        this.phuongTienService = phuongTienService;
        this.theCuDanService = theCuDanService;
        this.cuDanService = cuDanService;
        this.hoaDonDichVuService = hoaDonDichVuService;
    }

    @PostMapping
    public ResponseEntity<CanHoDto> save(@RequestBody @Validated CanHoDto canHoDto) {
        canHoService.save(canHoDto);
        return ResponseEntity.ok(canHoDto);
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
        return ResponseEntity.ok(canHoList);
    }

    @GetMapping("/khong-hoat-dong")
    public ResponseEntity<List<CanHoDto>> findAllByTrangThaiKhongHoatDong() {
        List<CanHoDto> canHoList = canHoService.findAllByTrangThai(false);
        return ResponseEntity.ok(canHoList);
    }

    // ------------------------------------------------------------------------------------------------------------- //


    @GetMapping("/{id}/cu-dan")
    public ResponseEntity<?> findAllCuDanByCanHo_Id(@PathVariable("id") Long id) {
        List<CuDanDto> cuDanList = cuDanService.findAllByCanHo_Id(id);
        return ResponseEntity.ok(cuDanList);
    }

    @GetMapping("/{id}/phuong-tien")
    public ResponseEntity<?> findAllPhuongTienByCanHo_Id(@PathVariable("id") Long id) {
        List<PhuongTienDto> cuDanList = phuongTienService.findAllByCanHo_Id(id);
        return ResponseEntity.ok(cuDanList);
    }

    @GetMapping("/{id}/the-cu-dan")
    public ResponseEntity<?> findAllTheCuDanByCanHo_Id(@PathVariable("id") Long id) {
        List<TheCuDanDto> cuDanList = theCuDanService.findAllByCanHo_Id(id);
        return ResponseEntity.ok(cuDanList);
    }

    @GetMapping("/{id}/hoa-don")
    public ResponseEntity<?> findAllHoaDonDichVuByCanHo_Id(@PathVariable("id") Long id) {
        List<HoaDonDichVuDto> cuDanList = hoaDonDichVuService.findAllByCanHo_Id(id);
        return ResponseEntity.ok(cuDanList);
    }

}