package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.BoPhanDto;
import com.nhom43.quanlychungcubackendgradle.dto.ChiTietHoaDonDichVuDto;
import com.nhom43.quanlychungcubackendgradle.entity.ChiTietHoaDonDichVu;
import com.nhom43.quanlychungcubackendgradle.mapper.ChiTietHoaDonDichVuMapper;
import com.nhom43.quanlychungcubackendgradle.repository.ChiTietHoaDonDichVuRepository;
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
public class ChiTietHoaDonDichVuService {
    private final ChiTietHoaDonDichVuRepository repository;
    private final ChiTietHoaDonDichVuMapper chiTietHoaDonDichVuMapper;

    public ChiTietHoaDonDichVuService(ChiTietHoaDonDichVuRepository repository, ChiTietHoaDonDichVuMapper chiTietHoaDonDichVuMapper) {
        this.repository = repository;
        this.chiTietHoaDonDichVuMapper = chiTietHoaDonDichVuMapper;
    }

    public ChiTietHoaDonDichVuDto save(ChiTietHoaDonDichVuDto chiTietHoaDonDichVuDto) {
        ChiTietHoaDonDichVu entity = chiTietHoaDonDichVuMapper.toEntity(chiTietHoaDonDichVuDto);
        return chiTietHoaDonDichVuMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ChiTietHoaDonDichVuDto findById(Long id) {
        return chiTietHoaDonDichVuMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<ChiTietHoaDonDichVuDto> findByCondition(ChiTietHoaDonDichVuDto chiTietHoaDonDichVuDto, Pageable pageable) {
        ChiTietHoaDonDichVu chiTietHoaDonDichVu = chiTietHoaDonDichVuMapper.toEntity(chiTietHoaDonDichVuDto);
        Page<ChiTietHoaDonDichVu> entityPage = repository.findAll(pageable);
        List<ChiTietHoaDonDichVu> entities = entityPage.getContent();
        return new PageImpl<>(chiTietHoaDonDichVuMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ChiTietHoaDonDichVuDto update(ChiTietHoaDonDichVuDto chiTietHoaDonDichVuDto, Long id) {
        ChiTietHoaDonDichVuDto data = findById(id);
        BeanUtils.copyProperties(chiTietHoaDonDichVuDto, data);
        return save(data);
    }
}