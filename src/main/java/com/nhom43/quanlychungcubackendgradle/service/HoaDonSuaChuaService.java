package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.HoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.HoaDonSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonSuaChuaRepository;
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
public class HoaDonSuaChuaService {
    private final HoaDonSuaChuaRepository repository;
    private final HoaDonSuaChuaMapper hoaDonSuaChuaMapper;

    public HoaDonSuaChuaService(HoaDonSuaChuaRepository repository, HoaDonSuaChuaMapper hoaDonSuaChuaMapper) {
        this.repository = repository;
        this.hoaDonSuaChuaMapper = hoaDonSuaChuaMapper;
    }

    public HoaDonSuaChuaDto save(HoaDonSuaChuaDto hoaDonSuaChuaDto) {
        HoaDonSuaChua entity = hoaDonSuaChuaMapper.toEntity(hoaDonSuaChuaDto);
        return hoaDonSuaChuaMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public HoaDonSuaChuaDto findById(Long id) {
        return hoaDonSuaChuaMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<HoaDonSuaChuaDto> findByCondition(HoaDonSuaChuaDto hoaDonSuaChuaDto, Pageable pageable) {
        HoaDonSuaChua hoaDonSuaChua = hoaDonSuaChuaMapper.toEntity(hoaDonSuaChuaDto);
        Page<HoaDonSuaChua> entityPage = repository.findAll(pageable);
        List<HoaDonSuaChua> entities = entityPage.getContent();
        return new PageImpl<>(hoaDonSuaChuaMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public HoaDonSuaChuaDto update(HoaDonSuaChuaDto hoaDonSuaChuaDto, Long id) {
        HoaDonSuaChuaDto data = findById(id);
        HoaDonSuaChua entity = hoaDonSuaChuaMapper.toEntity(hoaDonSuaChuaDto);
        BeanUtils.copyProperties(data, entity);
        return save(hoaDonSuaChuaMapper.toDto(entity));
    }
}