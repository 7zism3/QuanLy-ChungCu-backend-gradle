package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.NhanVienDto;
import com.nhom43.quanlychungcubackendgradle.entity.NhanVien;
import com.nhom43.quanlychungcubackendgradle.mapper.NhanVienMapper;
import com.nhom43.quanlychungcubackendgradle.repository.NhanVienRepository;
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
public class NhanVienService {
    private final NhanVienRepository repository;
    private final NhanVienMapper nhanVienMapper;

    public NhanVienService(NhanVienRepository repository, NhanVienMapper nhanVienMapper) {
        this.repository = repository;
        this.nhanVienMapper = nhanVienMapper;
    }

    public NhanVienDto save(NhanVienDto nhanVienDto) {
        NhanVien entity = nhanVienMapper.toEntity(nhanVienDto);
        return nhanVienMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public NhanVienDto findById(Long id) {
        return nhanVienMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<NhanVienDto> findByCondition(NhanVienDto nhanVienDto, Pageable pageable) {
        NhanVien nhanVien = nhanVienMapper.toEntity(nhanVienDto);
        Page<NhanVien> entityPage = repository.findAll(pageable);
        List<NhanVien> entities = entityPage.getContent();
        return new PageImpl<>(nhanVienMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public NhanVienDto update(NhanVienDto nhanVienDto, Long id) {
        NhanVienDto data = findById(id);
        BeanUtils.copyProperties(nhanVienDto, data);
        return save(data);
    }
}