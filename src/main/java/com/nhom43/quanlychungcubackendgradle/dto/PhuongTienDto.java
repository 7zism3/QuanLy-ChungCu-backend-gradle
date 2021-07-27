package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.entity.TheCuDan;

public class PhuongTienDto extends AbstractDto<Long> {
    private Long id;
    private String loaiXe;
    private String tenXe;
    private String bienKiemSoat;
    private CuDan cuDan;
    private TheCuDan theCuDan;

    public PhuongTienDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getLoaiXe() {
        return this.loaiXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getTenXe() {
        return this.tenXe;
    }

    public void setBienKiemSoat(String bienKiemSoat) {
        this.bienKiemSoat = bienKiemSoat;
    }

    public String getBienKiemSoat() {
        return this.bienKiemSoat;
    }

    public void setCuDan(CuDan cuDan) {
        this.cuDan = cuDan;
    }

    public CuDan getCuDan() {
        return this.cuDan;
    }

    public void setTheCuDan(TheCuDan theCuDan) {
        this.theCuDan = theCuDan;
    }

    public TheCuDan getTheCuDan() {
        return this.theCuDan;
    }
}