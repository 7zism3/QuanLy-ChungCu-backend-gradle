package com.nhom43.quanlychungcubackendgradle.dto;

public class TaiKhoanDto extends AbstractDto<Long> {
    private Long id;
    private String taiKhoan;
    private String matKhau;
    private String phanQuyen;
    private String soDienThoai;
    private String email;

    public TaiKhoanDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMatKhau() {
        return this.matKhau;
    }

    public void setPhanQuyen(String phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public String getPhanQuyen() {
        return this.phanQuyen;
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
}