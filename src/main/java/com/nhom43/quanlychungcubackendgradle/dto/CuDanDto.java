package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;

import java.time.LocalDate;

public class CuDanDto extends AbstractDto<Long> {
    private Long id;
    private String hoTen;
    private Boolean gioiTinh;
    private LocalDate ngaySinh;
    private String soCCCD;
    private String diaChi;
    private String hinhAnh;
    private String soDienThoai;
    private String email;
    private Boolean chuCanHo;
    private CanHo canHo;

    public CuDanDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Boolean getGioiTinh() {
        return this.gioiTinh;
    }

    public void setNgaySinh(java.time.LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public java.time.LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getSoCCCD() {
        return this.soCCCD;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHinhAnh() {
        return this.hinhAnh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setChuCanHo(Boolean chuCanHo) {
        this.chuCanHo = chuCanHo;
    }

    public Boolean getChuCanHo() {
        return this.chuCanHo;
    }

    public void setCanHo(CanHo canHo) {
        this.canHo = canHo;
    }

    public CanHo getCanHo() {
        return this.canHo;
    }
}