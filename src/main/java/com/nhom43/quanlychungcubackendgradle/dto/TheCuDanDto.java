package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;

import java.time.Instant;

public class TheCuDanDto extends AbstractDto<Long> {
    private Long id;
    private String maThe;
    private Instant ngayTao;
    private Boolean kichHoat;
    private CanHo canHo;

    public TheCuDanDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getMaThe() {
        return this.maThe;
    }

    public void setNgayTao(java.time.Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public java.time.Instant getNgayTao() {
        return this.ngayTao;
    }

    public void setKichHoat(Boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    public Boolean getKichHoat() {
        return this.kichHoat;
    }

    public void setCanHo(CanHo canHo) {
        this.canHo = canHo;
    }

    public CanHo getCanHo() {
        return this.canHo;
    }
}