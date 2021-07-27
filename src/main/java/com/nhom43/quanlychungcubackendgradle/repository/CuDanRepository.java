package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CuDanRepository extends JpaRepository<CuDan, Long>, JpaSpecificationExecutor<CuDan> {
}