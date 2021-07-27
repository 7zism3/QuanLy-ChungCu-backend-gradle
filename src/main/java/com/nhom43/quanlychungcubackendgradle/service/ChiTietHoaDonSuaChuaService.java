package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonSuaChuaDto;
import com.nhom43.quanlychungcubackendgradle.entity.ChiTietHoaDonSuaChua;
import com.nhom43.quanlychungcubackendgradle.mapper.ChiTietHoaDonSuaChuaMapper;
import com.nhom43.quanlychungcubackendgradle.repository.ChiTietHoaDonSuaChuaRepository;
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
public class ChiTietHoaDonSuaChuaService {
    private final ChiTietHoaDonSuaChuaRepository repository;
    private final ChiTietHoaDonSuaChuaMapper chiTietHoaDonSuaChuaMapper;

    public ChiTietHoaDonSuaChuaService(ChiTietHoaDonSuaChuaRepository repository, ChiTietHoaDonSuaChuaMapper chiTietHoaDonSuaChuaMapper) {
        this.repository = repository;
        this.chiTietHoaDonSuaChuaMapper = chiTietHoaDonSuaChuaMapper;
    }

    public ChiTietHoaDonSuaChuaDto save(ChiTietHoaDonSuaChuaDto chiTietHoaDonSuaChuaDto) {
        ChiTietHoaDonSuaChua entity = chiTietHoaDonSuaChuaMapper.toEntity(chiTietHoaDonSuaChuaDto);
        return chiTietHoaDonSuaChuaMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ChiTietHoaDonSuaChuaDto findById(Long id) {
        return chiTietHoaDonSuaChuaMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<ChiTietHoaDonSuaChuaDto> findByCondition(ChiTietHoaDonSuaChuaDto chiTietHoaDonSuaChuaDto, Pageable pageable) {
        ChiTietHoaDonSuaChua chiTietHoaDonSuaChua = chiTietHoaDonSuaChuaMapper.toEntity(chiTietHoaDonSuaChuaDto);
        Page<ChiTietHoaDonSuaChua> entityPage = repository.findAll(pageable);
        List<ChiTietHoaDonSuaChua> entities = entityPage.getContent();
        return new PageImpl<>(chiTietHoaDonSuaChuaMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ChiTietHoaDonSuaChuaDto update(ChiTietHoaDonSuaChuaDto chiTietHoaDonSuaChuaDto, Long id) {
        ChiTietHoaDonSuaChuaDto data = findById(id);
        ChiTietHoaDonSuaChua entity = chiTietHoaDonSuaChuaMapper.toEntity(chiTietHoaDonSuaChuaDto);
        BeanUtils.copyProperties(data, entity);
        return save(chiTietHoaDonSuaChuaMapper.toDto(entity));
    }
}