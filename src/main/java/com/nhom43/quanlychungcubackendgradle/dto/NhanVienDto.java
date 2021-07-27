package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.BoPhan;

public class NhanVienDto extends AbstractDto<Long> {
    private Long id;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String bienKiemSoat;
    private BoPhan boPhan;

    public NhanVienDto() {
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

    public void setBienKiemSoat(String bienKiemSoat) {
        this.bienKiemSoat = bienKiemSoat;
    }

    public String getBienKiemSoat() {
        return this.bienKiemSoat;
    }

    public void setBoPhan(BoPhan boPhan) {
        this.boPhan = boPhan;
    }

    public BoPhan getBoPhan() {
        return this.boPhan;
    }
}