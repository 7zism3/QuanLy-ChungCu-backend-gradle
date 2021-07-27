package com.nhom43.quanlychungcubackendgradle.dto;

public class CanHoDto extends AbstractDto<Long> {
    private Long id;
    private String tenCanHo;
    private String moTa;
    private Integer dienTich;
    private Boolean trangThai;

    public CanHoDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTenCanHo(String tenCanHo) {
        this.tenCanHo = tenCanHo;
    }

    public String getTenCanHo() {
        return this.tenCanHo;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public void setDienTich(Integer dienTich) {
        this.dienTich = dienTich;
    }

    public Integer getDienTich() {
        return this.dienTich;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getTrangThai() {
        return this.trangThai;
    }
}