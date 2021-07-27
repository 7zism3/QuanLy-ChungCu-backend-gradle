package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonDichVuRepository extends JpaRepository<HoaDonDichVu, Long>, JpaSpecificationExecutor<HoaDonDichVu> {
}