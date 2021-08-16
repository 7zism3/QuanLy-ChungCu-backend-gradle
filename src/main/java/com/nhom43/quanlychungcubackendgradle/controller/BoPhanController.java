package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.BoPhanDto;
import com.nhom43.quanlychungcubackendgradle.service.BoPhanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/bo-phan")
@RestController
public class BoPhanController {
    private final BoPhanService boPhanService;

    public BoPhanController(BoPhanService boPhanService) {
        this.boPhanService = boPhanService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated BoPhanDto boPhanDto) {
        boPhanService.save(boPhanDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoPhanDto> findById(@PathVariable("id") Long id) {
        BoPhanDto boPhan = boPhanService.findById(id);
        return ResponseEntity.ok(boPhan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        boPhanService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<BoPhanDto>> pageQuery(BoPhanDto boPhanDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoPhanDto> boPhanPage = boPhanService.findByCondition(boPhanDto, pageable);
        return ResponseEntity.ok(boPhanPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated BoPhanDto boPhanDto, @PathVariable("id") Long id) {
        boPhanService.update(boPhanDto, id);
        return ResponseEntity.ok().build();
    }

}