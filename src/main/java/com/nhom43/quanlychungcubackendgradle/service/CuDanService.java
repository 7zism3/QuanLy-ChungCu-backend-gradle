package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.CuDanDto;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.mapper.CuDanMapper;
import com.nhom43.quanlychungcubackendgradle.repository.CuDanRepository;
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
public class CuDanService {
    private final CuDanRepository repository;
    private final CuDanMapper cuDanMapper;

    public CuDanService(CuDanRepository repository, CuDanMapper cuDanMapper) {
        this.repository = repository;
        this.cuDanMapper = cuDanMapper;
    }

    public CuDanDto save(CuDanDto cuDanDto) {
        CuDan entity = cuDanMapper.toEntity(cuDanDto);
        return cuDanMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CuDanDto findById(Long id) {
        return cuDanMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<CuDanDto> findByCondition(CuDanDto cuDanDto, Pageable pageable) {
        CuDan cuDan = cuDanMapper.toEntity(cuDanDto);
        Page<CuDan> entityPage = repository.findAll(pageable);
        List<CuDan> entities = entityPage.getContent();
        return new PageImpl<>(cuDanMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public CuDanDto update(CuDanDto cuDanDto, Long id) {
        CuDanDto data = findById(id);
        CuDan entity = cuDanMapper.toEntity(cuDanDto);
        BeanUtils.copyProperties(data, entity);
        return save(cuDanMapper.toDto(entity));
    }
}