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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

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
        BeanUtils.copyProperties(phuongTienDto, data);
        return save(data);
    }


    // ------------------------------------------------------------------------------------------------------------- //

    public List<PhuongTienDto> findAll() {
        List<PhuongTien> list = repository.findAll();
        if (list.isEmpty()) throw new ResourceNotFoundException("Chưa tồn tại phương tiện nào");
        return phuongTienMapper.toDto(list);
    }

    public List<PhuongTienDto> findAllByCanHo_Id(Long id_canHo) {

        List<PhuongTien> list = repository.findAllByCanHo_Id(id_canHo);

        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chưa có phương tiện nào trong căn hộ này");
        return phuongTienMapper.toDto(list);
    }
}