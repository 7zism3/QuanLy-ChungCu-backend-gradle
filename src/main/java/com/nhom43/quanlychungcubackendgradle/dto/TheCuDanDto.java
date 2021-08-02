package com.nhom43.quanlychungcubackendgradle.dto;

import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TheCuDanDto extends AbstractDto<Long> {

    private Long id;
    private String maThe;
    private Instant ngayTao;
    private Boolean kichHoat;
    private CanHo canHo;

}