package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;

public class HoaDonDichVuDto extends AbstractDto<Long> {
    private Long id;
    private Integer thang;
    private Integer nam;
    private Boolean trangThai;
    private String bienKiemSoat;
    private CanHo canHo;

    public HoaDonDichVuDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    public Integer getThang() {
        return this.thang;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getNam() {
        return this.nam;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Boolean getTrangThai() {
        return this.trangThai;
    }

    public void setBienKiemSoat(String bienKiemSoat) {
        this.bienKiemSoat = bienKiemSoat;
    }

    public String getBienKiemSoat() {
        return this.bienKiemSoat;
    }

    public void setCanHo(CanHo canHo) {
        this.canHo = canHo;
    }

    public CanHo getCanHo() {
        return this.canHo;
    }
}