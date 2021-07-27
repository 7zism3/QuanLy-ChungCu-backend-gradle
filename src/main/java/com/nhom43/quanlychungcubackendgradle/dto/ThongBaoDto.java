package com.nhom43.quanlychungcubackendgradle.dto;

import java.time.LocalTime;

public class ThongBaoDto extends AbstractDto<Long> {
    private Long id;
    private String tieuDe;
    private String noiDung;
    private LocalTime thoiGian;

    public ThongBaoDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTieuDe() {
        return this.tieuDe;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public void setThoiGian(java.time.LocalTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public java.time.LocalTime getThoiGian() {
        return this.thoiGian;
    }
}