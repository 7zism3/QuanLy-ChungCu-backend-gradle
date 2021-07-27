package com.nhom43.quanlychungcubackendgradle.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//---------
@Entity
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String taiKhoan;
    @NonNull
    private String matKhau;
    @NonNull
    private String phanQuyen;
    @NonNull
    private String soDienThoai;
    @NonNull
    private String email;

}
