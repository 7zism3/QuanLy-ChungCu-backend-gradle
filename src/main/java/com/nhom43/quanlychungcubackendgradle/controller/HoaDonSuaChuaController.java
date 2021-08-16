package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.dto.HoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.dto.HoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.HoaDonSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.service.ChiTietHoaDonDichVuService;
import com.nhom43.quanlychungcubackendgradle.service.ChiTietHoaDonSuaChuaService;
import com.nhom43.quanlychungcubackendgradle.service.HoaDonSuaChuaService;
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

@RequestMapping("/api/hoa-don-sua-chua")
@RestController
public class HoaDonSuaChuaController {
    private final HoaDonSuaChuaService hoaDonSuaChuaService;
    private final ChiTietHoaDonSuaChuaService chiTietHoaDonSuaChuaService;

    public HoaDonSuaChuaController(HoaDonSuaChuaService hoaDonSuaChuaService,
                                   ChiTietHoaDonSuaChuaService chiTietHoaDonSuaChuaService) {
        this.hoaDonSuaChuaService = hoaDonSuaChuaService;
        this.chiTietHoaDonSuaChuaService = chiTietHoaDonSuaChuaService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated HoaDonSuaChuaDto hoaDonSuaChuaDto) {
        hoaDonSuaChuaService.save(hoaDonSuaChuaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoaDonSuaChuaDto> findById(@PathVariable("id") Long id) {
        HoaDonSuaChuaDto hoaDonSuaChua = hoaDonSuaChuaService.findById(id);
        return ResponseEntity.ok(hoaDonSuaChua);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        hoaDonSuaChuaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<HoaDonSuaChuaDto>> pageQuery(HoaDonSuaChuaDto hoaDonSuaChuaDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<HoaDonSuaChuaDto> hoaDonSuaChuaPage = hoaDonSuaChuaService.findByCondition(hoaDonSuaChuaDto, pageable);
        return ResponseEntity.ok(hoaDonSuaChuaPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated HoaDonSuaChuaDto hoaDonSuaChuaDto, @PathVariable("id") Long id) {
        hoaDonSuaChuaService.update(hoaDonSuaChuaDto, id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------------------------------------------------------------------------- //

    @GetMapping("")
    public ResponseEntity<List<HoaDonSuaChuaDto>> findAll() {
        List<HoaDonSuaChuaDto> list = hoaDonSuaChuaService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<List<HoaDonSuaChuaDto>> findAllByDaThanhToan() {
        List<HoaDonSuaChuaDto> list = hoaDonSuaChuaService.findAllByTrangThai(true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/chua-thanh-toan")
    public ResponseEntity<List<HoaDonSuaChuaDto>> findAllByChuaThanhToan() {
        List<HoaDonSuaChuaDto> list = hoaDonSuaChuaService.findAllByTrangThai(false);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/chi-tiet-hoa-don-dich-vu")
    public ResponseEntity<?> findAllCCHHDVByHoaDonDichVu_CanHo_Id(@PathVariable("id") Long id) {
        List<ChiTietHoaDonSuaChuaDto> list = chiTietHoaDonSuaChuaService.findByHoaDonSuaChua_Id(id);
        return ResponseEntity.ok(list);
    }
}