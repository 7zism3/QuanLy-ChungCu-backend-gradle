package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.entity.PhuongTien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhuongTienRepository extends JpaRepository<PhuongTien, Long>, JpaSpecificationExecutor<PhuongTien> {

    List<PhuongTien> findAllByCuDan_CanHo_Id(Long id);

}