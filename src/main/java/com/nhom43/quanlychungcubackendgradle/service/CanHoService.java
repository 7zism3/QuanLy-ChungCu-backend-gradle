package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.CanHoDto;
import com.nhom43.quanlychungcubackendgradle.entity.CanHo;
import com.nhom43.quanlychungcubackendgradle.entity.CuDan;
import com.nhom43.quanlychungcubackendgradle.mapper.CanHoMapper;
import com.nhom43.quanlychungcubackendgradle.repository.CanHoRepository;
import com.nhom43.quanlychungcubackendgradle.repository.CuDanRepository;
import com.nhom43.quanlychungcubackendgradle.repository.UserRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CanHoService {
    private final CanHoRepository repository;
    private final CuDanRepository cuDanRepository;
    private final CanHoMapper canHoMapper;
    private final UserRepository userRepository;

    public CanHoService(CanHoRepository repository
            , CanHoMapper canHoMapper
            , CuDanRepository cuDanRepository
            , UserRepository userRepository) {
        this.repository = repository;
        this.canHoMapper = canHoMapper;
        this.cuDanRepository = cuDanRepository;
        this.userRepository = userRepository;
    }

    public CanHoDto save(CanHoDto canHoDto) {
        CanHo entity = canHoMapper.toEntity(canHoDto);
        return canHoMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public CanHoDto findById(Long id) {
        return canHoMapper.toDto(repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại căn hộ id: " + id)));
    }

    public Page<CanHoDto> findByCondition(CanHoDto canHoDto, Pageable pageable) {
        CanHo canHo = canHoMapper.toEntity(canHoDto);
        Page<CanHo> entityPage = repository.findAll(pageable);
        List<CanHo> entities = entityPage.getContent();
        return new PageImpl<>(canHoMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

//    public CanHoDto update(CanHoDto canHoDto, Long id) {
//        CanHoDto data = findById(id);
//        CanHo entity = canHoMapper.toEntity(canHoDto);
//        BeanUtils.copyProperties(data, entity);
//        return save(canHoMapper.toDto(entity));
//    }

    public CanHoDto update(CanHoDto canHoDto, Long id) {
        CanHoDto data = findById(id);
        BeanUtils.copyProperties(canHoDto, data);
        return save(data);
    }

    // ------------------------------------------------------------------------------------------------------------- //


    public List<CanHoDto> findAll() {
        List<CanHo> canHoList = repository.findAll();
        if (canHoList.isEmpty()) throw new ResourceNotFoundException("Chưa tồn tại căn hộ");
//        if (canHoList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chưa tồn tại căn hộ");
        return canHoMapper.toDto(canHoList);
    }

    public List<CanHoDto> findAllByTrangThai(boolean trangThai) {
        List<CanHo> canHoList = repository.findAllByTrangThai(trangThai);
        if (trangThai == true) {
            List<CanHoDto> canHoListDto = new ArrayList<>();
            for (CanHo canHo : canHoList) {
                CuDan chuCanHo = cuDanRepository.findByCanHoAndChuCanHo(canHo, true);
                CanHoDto canHoDto = canHoMapper.toDto(canHo);
                Long setSoLuongCuDan = cuDanRepository.countByCanHoAndDaXoa(canHo, false);
                String emailTaiKhoan = "";
                if (canHoDto.getIdTaiKhoan() != null)
                    emailTaiKhoan = userRepository.findById(canHoDto.getIdTaiKhoan()).get().getEmail();
                canHoDto.setChuCanHo(chuCanHo);
                canHoDto.setSoLuongCuDan(setSoLuongCuDan);
                canHoDto.setEmailTaiKhoan(emailTaiKhoan);
                canHoListDto.add(canHoDto);
            }
            if (canHoList.isEmpty()) throw new ResourceNotFoundException("Chưa tồn tại căn hộ");
            return canHoListDto;
        } else {
            if (canHoList.isEmpty()) throw new ResourceNotFoundException("Chưa tồn tại căn hộ");
            return canHoMapper.toDto(canHoList);
        }
    }
}