package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua;
import com.nhom43.quanlychungcubackendgradle.entity.NhanVien;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChiTietHoaDonSuaChuaDto extends AbstractDto<Long> {

    private Long id;
    private Double donGia;
    private Integer soLuong;
    private String moTa;
    private NhanVien nhanVien;
    private LoaiSuaChua LoaiSuaChua;
    private HoaDonSuaChua hoaDonSuaChua;

}