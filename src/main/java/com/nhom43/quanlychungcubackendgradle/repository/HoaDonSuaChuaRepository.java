package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonSuaChuaRepository extends JpaRepository<HoaDonSuaChua, Long>, JpaSpecificationExecutor<HoaDonSuaChua> {
    List<HoaDonSuaChua> findAllByOrderByTrangThaiAscNgayTaoDesc();

    List<HoaDonSuaChua> findAllByCanHo_IdOrderByTrangThaiAscNgayTaoDesc(Long id_canHo);
}