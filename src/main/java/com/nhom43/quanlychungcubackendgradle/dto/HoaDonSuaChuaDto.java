package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;

import java.time.Instant;

public class HoaDonSuaChuaDto extends AbstractDto<Long> {
    private Long id;
    private Instant ngay;
    private Boolean trangThai;
    private CanHo canHo;

    public HoaDonSuaChuaDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNgay(java.time.Instant ngay) {
        this.ngay = ngay;
    }

    public java.time.Instant getNgay() {
        return this.ngay;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getTrangThai() {
        return this.trangThai;
    }

    public void setCanHo(CanHo canHo) {
        this.canHo = canHo;
    }

    public CanHo getCanHo() {
        return this.canHo;
    }
}