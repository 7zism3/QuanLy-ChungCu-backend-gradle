
package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HoaDonSuaChua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate ngayTao;

    private Boolean trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_can_ho", nullable = false)
    private CanHo canHo;
}