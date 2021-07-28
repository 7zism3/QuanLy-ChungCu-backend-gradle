package com.nhom43.quanlychungcubackendgradle.repository;

import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.entity.TheCuDan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheCuDanRepository extends JpaRepository<TheCuDan, Long>, JpaSpecificationExecutor<TheCuDan> {

    List<TheCuDan> findAllByCanHo_Id(Long id);

}