package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HoaDonDichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant ngayTao;

    private Boolean trangThai;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_can_ho", nullable = false)
    private CanHo canHo;
}