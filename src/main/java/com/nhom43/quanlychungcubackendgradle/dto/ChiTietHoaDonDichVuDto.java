package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.DichVuCoDinh;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;

public class ChiTietHoaDonDichVuDto extends AbstractDto<Long> {
    private Long id;
    private Double donGia;
    private Integer soLuong;
    private DichVuCoDinh dichVuCoDinh;
    private HoaDonDichVu hoaDonDichVu;

    public ChiTietHoaDonDichVuDto() {
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

    public void setDichVuCoDinh(DichVuCoDinh dichVuCoDinh) {
        this.dichVuCoDinh = dichVuCoDinh;
    }

    public DichVuCoDinh getDichVuCoDinh() {
        return this.dichVuCoDinh;
    }

    public void setHoaDonDichVu(HoaDonDichVu hoaDonDichVu) {
        this.hoaDonDichVu = hoaDonDichVu;
    }

    public HoaDonDichVu getHoaDonDichVu() {
        return this.hoaDonDichVu;
    }
}