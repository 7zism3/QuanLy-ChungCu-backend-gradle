package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.dto.HoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.dto.HoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.mapper.ChiTietHoaDonDichVuMapper;
import com.nhom43.quanlychungcubackendgradle.mapper.HoaDonDichVuMapper;
import com.nhom43.quanlychungcubackendgradle.service.ChiTietHoaDonDichVuService;
import com.nhom43.quanlychungcubackendgradle.service.HoaDonDichVuService;
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

@RequestMapping("/api/hoa-don-dich-vu")
@RestController
public class HoaDonDichVuController {
    private final HoaDonDichVuService hoaDonDichVuService;
    private final ChiTietHoaDonDichVuService chiTietHoaDonDichVuService;

    public HoaDonDichVuController(HoaDonDichVuService hoaDonDichVuService, ChiTietHoaDonDichVuService chiTietHoaDonDichVuService) {
        this.hoaDonDichVuService = hoaDonDichVuService;
        this.chiTietHoaDonDichVuService = chiTietHoaDonDichVuService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated HoaDonDichVuDto hoaDonDichVuDto) {
        hoaDonDichVuService.save(hoaDonDichVuDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoaDonDichVuDto> findById(@PathVariable("id") Long id) {
        HoaDonDichVuDto hoaDonDichVu = hoaDonDichVuService.findById(id);
        return ResponseEntity.ok(hoaDonDichVu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        hoaDonDichVuService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<HoaDonDichVuDto>> pageQuery(HoaDonDichVuDto hoaDonDichVuDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<HoaDonDichVuDto> hoaDonDichVuPage = hoaDonDichVuService.findByCondition(hoaDonDichVuDto, pageable);
        return ResponseEntity.ok(hoaDonDichVuPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated HoaDonDichVuDto hoaDonDichVuDto, @PathVariable("id") Long id) {
        hoaDonDichVuService.update(hoaDonDichVuDto, id);
        return ResponseEntity.ok().build();
    }

    // ------------------------------------------------------------------------------------------------------------- //

    @GetMapping("")
    public ResponseEntity<List<HoaDonDichVuDto>> findAll() {
        List<HoaDonDichVuDto> list = hoaDonDichVuService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/da-thanh-toan")
    public ResponseEntity<List<HoaDonDichVuDto>> findAllByDaThanhToan() {
        List<HoaDonDichVuDto> list = hoaDonDichVuService.findAllByTrangThai(true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/chua-thanh-toan")
    public ResponseEntity<List<HoaDonDichVuDto>> findAllByChuaThanhToan() {
        List<HoaDonDichVuDto> list = hoaDonDichVuService.findAllByTrangThai(false);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}/chi-tiet-hoa-don-dich-vu")
    public ResponseEntity<?> findAllCCHHDVByHoaDonDichVu_CanHo_Id(@PathVariable("id") Long id) {
        List<ChiTietHoaDonDichVuDto> list = chiTietHoaDonDichVuService.findAllByHoaDonDichVu_Id(id);
        return ResponseEntity.ok(list);
    }
}