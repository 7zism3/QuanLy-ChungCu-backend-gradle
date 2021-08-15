package com.nhom43.quanlychungcubackendgradle.controller;

import com.nhom43.quanlychungcubackendgradle.dto.RefreshTokenDto;
import com.nhom43.quanlychungcubackendgradle.entity.RefreshToken;
import com.nhom43.quanlychungcubackendgradle.mapper.RefreshTokenMapper;
import com.nhom43.quanlychungcubackendgradle.service.RefreshTokenService2;
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

@RequestMapping("/api/refresh-token")
@RestController
public class RefreshTokenController {
    private final RefreshTokenService2 refreshTokenService;

    public RefreshTokenController(RefreshTokenService2 refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated RefreshTokenDto refreshTokenDto) {
        refreshTokenService.save(refreshTokenDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefreshTokenDto> findById(@PathVariable("id") Long id) {
        RefreshTokenDto refreshToken = refreshTokenService.findById(id);
        return ResponseEntity.ok(refreshToken);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        refreshTokenService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<RefreshTokenDto>> pageQuery(RefreshTokenDto refreshTokenDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<RefreshTokenDto> refreshTokenPage = refreshTokenService.findByCondition(refreshTokenDto, pageable);
        return ResponseEntity.ok(refreshTokenPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated RefreshTokenDto refreshTokenDto, @PathVariable("id") Long id) {
        refreshTokenService.update(refreshTokenDto, id);
        return ResponseEntity.ok().build();
    }
}