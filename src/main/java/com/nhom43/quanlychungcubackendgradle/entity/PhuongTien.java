package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhuongTien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loaiXe;

    private String tenXe;

    private String bienKiemSoat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cu_dan", nullable = false)
    private CuDan cuDan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_the_cu_dan", nullable = false)
    private TheCuDan theCuDan;
}