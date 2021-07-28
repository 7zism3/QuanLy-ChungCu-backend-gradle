package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.LoaiSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.LoaiSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.LoaiSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.repository.LoaiSuaChuaRepository;
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
public class LoaiSuaChuaService {
    private final LoaiSuaChuaRepository repository;
    private final LoaiSuaChuaMapper loaiSuaChuaMapper;

    public LoaiSuaChuaService(LoaiSuaChuaRepository repository, LoaiSuaChuaMapper loaiSuaChuaMapper) {
        this.repository = repository;
        this.loaiSuaChuaMapper = loaiSuaChuaMapper;
    }

    public LoaiSuaChuaDto save(LoaiSuaChuaDto loaiSuaChuaDto) {
        LoaiSuaChua entity = loaiSuaChuaMapper.toEntity(loaiSuaChuaDto);
        return loaiSuaChuaMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public LoaiSuaChuaDto findById(Long id) {
        return loaiSuaChuaMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<LoaiSuaChuaDto> findByCondition(LoaiSuaChuaDto loaiSuaChuaDto, Pageable pageable) {
        LoaiSuaChua loaiSuaChua = loaiSuaChuaMapper.toEntity(loaiSuaChuaDto);
        Page<LoaiSuaChua> entityPage = repository.findAll(pageable);
        List<LoaiSuaChua> entities = entityPage.getContent();
        return new PageImpl<>(loaiSuaChuaMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public LoaiSuaChuaDto update(LoaiSuaChuaDto loaiSuaChuaDto, Long id) {
        LoaiSuaChuaDto data = findById(id);
        BeanUtils.copyProperties(loaiSuaChuaDto, data);
        return save(data);
    }
}