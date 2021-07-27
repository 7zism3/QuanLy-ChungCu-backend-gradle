package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HoaDonDichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer thang;

    private Integer nam;

    private Boolean trangThai;

    private String bienKiemSoat;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_can_ho", nullable = false)
    private CanHo canHo;
}