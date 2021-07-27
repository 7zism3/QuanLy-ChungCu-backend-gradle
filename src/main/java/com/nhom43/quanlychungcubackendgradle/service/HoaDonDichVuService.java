package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.HoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.entity.HoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.mapper.HoaDonDichVuMapper;
import com.nhom43.quanlychungcubackendgradle.repository.HoaDonDichVuRepository;
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
public class HoaDonDichVuService {
    private final HoaDonDichVuRepository repository;
    private final HoaDonDichVuMapper hoaDonDichVuMapper;

    public HoaDonDichVuService(HoaDonDichVuRepository repository, HoaDonDichVuMapper hoaDonDichVuMapper) {
        this.repository = repository;
        this.hoaDonDichVuMapper = hoaDonDichVuMapper;
    }

    public HoaDonDichVuDto save(HoaDonDichVuDto hoaDonDichVuDto) {
        HoaDonDichVu entity = hoaDonDichVuMapper.toEntity(hoaDonDichVuDto);
        return hoaDonDichVuMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public HoaDonDichVuDto findById(Long id) {
        return hoaDonDichVuMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<HoaDonDichVuDto> findByCondition(HoaDonDichVuDto hoaDonDichVuDto, Pageable pageable) {
        HoaDonDichVu hoaDonDichVu = hoaDonDichVuMapper.toEntity(hoaDonDichVuDto);
        Page<HoaDonDichVu> entityPage = repository.findAll(pageable);
        List<HoaDonDichVu> entities = entityPage.getContent();
        return new PageImpl<>(hoaDonDichVuMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public HoaDonDichVuDto update(HoaDonDichVuDto hoaDonDichVuDto, Long id) {
        HoaDonDichVuDto data = findById(id);
        HoaDonDichVu entity = hoaDonDichVuMapper.toEntity(hoaDonDichVuDto);
        BeanUtils.copyProperties(data, entity);
        return save(hoaDonDichVuMapper.toDto(entity));
    }
}