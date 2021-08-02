package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.entity.TheCuDan;

public class PhuongTienDto extends AbstractDto<Long> {

    private Long id;
    private String loaiXe;
    private String tenXe;
    private String bienKiemSoat;
    private CanHo canHo;
    private TheCuDan theCuDan;

}