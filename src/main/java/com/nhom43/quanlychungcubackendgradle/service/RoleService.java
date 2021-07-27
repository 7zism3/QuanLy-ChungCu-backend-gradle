package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.RoleDto;
import com.nhom43.quanlychungcubackendgradle.entity.Role;
import com.nhom43.quanlychungcubackendgradle.mapper.RoleMapper;
import com.nhom43.quanlychungcubackendgradle.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository repository, RoleMapper roleMapper) {
        this.repository = repository;
        this.roleMapper = roleMapper;
    }

    public RoleDto save(RoleDto roleDto) {
        Role entity = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(repository.save(entity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public RoleDto findById(Integer id) {
        return roleMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<RoleDto> findByCondition(RoleDto roleDto, Pageable pageable) {
        Role role = roleMapper.toEntity(roleDto);
        Page<Role> entityPage = repository.findAll(pageable);
        List<Role> entities = entityPage.getContent();
        return new PageImpl<>(roleMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public RoleDto update(RoleDto roleDto, Integer id) {
        RoleDto data = findById(id);
        Role entity = roleMapper.toEntity(roleDto);
        BeanUtil.copyProperties(data, entity);
        return save(roleMapper.toDto(entity));
    }
}