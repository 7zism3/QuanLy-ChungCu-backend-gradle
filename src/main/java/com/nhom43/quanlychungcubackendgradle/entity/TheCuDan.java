package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TheCuDan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maThe;

    private Instant ngayTao;

    private Boolean kichHoat;

    private Boolean daXoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_can_ho", nullable = false)
    private CanHo canHo;
}