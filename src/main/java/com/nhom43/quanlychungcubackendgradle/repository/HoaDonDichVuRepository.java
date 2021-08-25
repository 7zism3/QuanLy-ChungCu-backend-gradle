package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonDichVuRepository extends JpaRepository<HoaDonDichVu, Long>, JpaSpecificationExecutor<HoaDonDichVu> {

    List<HoaDonDichVu> findAllByOrderByTrangThaiAscNgayTaoDesc();

    List<HoaDonDichVu> findAllByCanHo_IdOrderByTrangThaiAscNgayTaoDesc(Long idCanHo);

    @Query("SELECT COUNT (u) FROM HoaDonDichVu u WHERE " +
            "(u.trangThai = ?1) " +
            " and (SUBSTRING(CURRENT_DATE(), 1, 7) = SUBSTRING(u.ngayTao, 1, 7))")
    Integer countHoaDonDichVuByTrangThai(Boolean trangThai);
}