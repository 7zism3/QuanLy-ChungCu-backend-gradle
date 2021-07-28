package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.ThongBaoDto;
import com.nhom43.quanlychungcubackendgradle.entity.ThongBao;
import com.nhom43.quanlychungcubackendgradle.mapper.ThongBaoMapper;
import com.nhom43.quanlychungcubackendgradle.repository.ThongBaoRepository;
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
public class ThongBaoService {
    private final ThongBaoRepository repository;
    private final ThongBaoMapper thongBaoMapper;

    public ThongBaoService(ThongBaoRepository repository, ThongBaoMapper thongBaoMapper) {
        this.repository = repository;
        this.thongBaoMapper = thongBaoMapper;
    }

    public ThongBaoDto save(ThongBaoDto thongBaoDto) {
        ThongBao entity = thongBaoMapper.toEntity(thongBaoDto);
        return thongBaoMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ThongBaoDto findById(Long id) {
        return thongBaoMapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tồn tại id: " + id)));
    }

    public Page<ThongBaoDto> findByCondition(ThongBaoDto thongBaoDto, Pageable pageable) {
        ThongBao thongBao = thongBaoMapper.toEntity(thongBaoDto);
        Page<ThongBao> entityPage = repository.findAll(pageable);
        List<ThongBao> entities = entityPage.getContent();
        return new PageImpl<>(thongBaoMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ThongBaoDto update(ThongBaoDto thongBaoDto, Long id) {
        ThongBaoDto data = findById(id);
        BeanUtils.copyProperties(thongBaoDto, data);
        return save(data);
    }
}