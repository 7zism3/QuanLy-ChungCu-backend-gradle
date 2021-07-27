package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.PhuongTienDto;
import com.nhom43.quanlychungcubackendgradle.entity.PhuongTien;
import com.nhom43.quanlychungcubackendgradle.mapper.PhuongTienMapper;
import com.nhom43.quanlychungcubackendgradle.repository.PhuongTienRepository;
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
public class PhuongTienService {
    private final PhuongTienRepository repository;
    private final PhuongTienMapper phuongTienMapper;

    public PhuongTienService(PhuongTienRepository repository, PhuongTienMapper phuongTienMapper) {
        this.repository = repository;
        this.phuongTienMapper = phuongTienMapper;
    }

    public PhuongTienDto save(PhuongTienDto phuongTienDto) {
        PhuongTien entity = phuongTienMapper.toEntity(phuongTienDto);
        return phuongTienMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PhuongTienDto findById(Long id) {
        return phuongTienMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)
        ));
    }

    public Page<PhuongTienDto> findByCondition(PhuongTienDto phuongTienDto, Pageable pageable) {
        PhuongTien phuongTien = phuongTienMapper.toEntity(phuongTienDto);
        Page<PhuongTien> entityPage = repository.findAll(pageable);
        List<PhuongTien> entities = entityPage.getContent();
        return new PageImpl<>(phuongTienMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public PhuongTienDto update(PhuongTienDto phuongTienDto, Long id) {
        PhuongTienDto data = findById(id);
        PhuongTien entity = phuongTienMapper.toEntity(phuongTienDto);
        BeanUtils.copyProperties(data, entity);
        return save(phuongTienMapper.toDto(entity));
    }
}