package com.nhom43.quanlychungcubackendgradle.dto;

public class DichVuCoDinhDto extends AbstractDto<Long> {
    private Long id;
    private String ten;
    private Double donGia;

    public DichVuCoDinhDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return this.ten;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDonGia() {
        return this.donGia;
    }
}