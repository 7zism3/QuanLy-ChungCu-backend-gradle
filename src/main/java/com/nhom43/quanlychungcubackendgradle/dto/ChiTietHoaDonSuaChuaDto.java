package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua;
import com.nhom43.quanlychungcubackendgradle.entity.NhanVien;

public class ChiTietHoaDonSuaChuaDto extends AbstractDto<Long> {
    private Long id;
    private Double donGia;
    private Integer soLuong;
    private String moTa;
    private NhanVien nhanVien;
    private LoaiSuaChua LoaiSuaChua;
    private HoaDonSuaChua hoaDonSuaChua;

    public ChiTietHoaDonSuaChuaDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDonGia() {
        return this.donGia;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getSoLuong() {
        return this.soLuong;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setLoaiSuaChua(com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua LoaiSuaChua) {
        this.LoaiSuaChua = LoaiSuaChua;
    }

    public com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua getLoaiSuaChua() {
        return this.LoaiSuaChua;
    }

    public void setHoaDonSuaChua(HoaDonSuaChua hoaDonSuaChua) {
        this.hoaDonSuaChua = hoaDonSuaChua;
    }

    public HoaDonSuaChua getHoaDonSuaChua() {
        return this.hoaDonSuaChua;
    }
}