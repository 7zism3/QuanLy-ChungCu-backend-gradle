package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.TaiKhoanDto;
import com.nhom43.quanlychungcubackendgradle.entity.TaiKhoan;
import com.nhom43.quanlychungcubackendgradle.mapper.TaiKhoanMapper;
import com.nhom43.quanlychungcubackendgradle.repository.TaiKhoanRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaiKhoanService {
    private final TaiKhoanRepository repository;
    private final TaiKhoanMapper taiKhoanMapper;

    public TaiKhoanService(TaiKhoanRepository repository, TaiKhoanMapper taiKhoanMapper) {
        this.repository = repository;
        this.taiKhoanMapper = taiKhoanMapper;
    }

    public TaiKhoanDto save(TaiKhoanDto taiKhoanDto) {
        TaiKhoan entity = taiKhoanMapper.toEntity(taiKhoanDto);
        return taiKhoanMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public TaiKhoanDto findById(Long id) {
        return taiKhoanMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<TaiKhoanDto> findByCondition(TaiKhoanDto taiKhoanDto, Pageable pageable) {
        TaiKhoan taiKhoan = taiKhoanMapper.toEntity(taiKhoanDto);
        Page<TaiKhoan> entityPage = repository.findAll(pageable);
        List<TaiKhoan> entities = entityPage.getContent();
        return new PageImpl<>(taiKhoanMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public TaiKhoanDto update(TaiKhoanDto taiKhoanDto, Long id) {
        TaiKhoanDto data = findById(id);
        TaiKhoan entity = taiKhoanMapper.toEntity(taiKhoanDto);
        BeanUtils.copyProperties(data, entity);
        return save(taiKhoanMapper.toDto(entity));
    }
}