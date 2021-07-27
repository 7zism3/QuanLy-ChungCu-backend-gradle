package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.ChiTietHoaDonSuaChua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietHoaDonSuaChuaRepository extends JpaRepository<ChiTietHoaDonSuaChua, Long>, JpaSpecificationExecutor<ChiTietHoaDonSuaChua> {
}