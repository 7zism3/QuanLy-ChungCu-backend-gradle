package com.nhom43.quanlychungcubackendgradle.service;

import com.nhom43.quanlychungcubackendgradle.dto.UserDto;
import com.nhom43.quanlychungcubackendgradle.entity.User;
import com.nhom43.quanlychungcubackendgradle.mapper.UserMapper;
import com.nhom43.quanlychungcubackendgradle.repository.UserRepository;
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
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserDto save(UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        return userMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public UserDto findById(Long id) {
        return userMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Page<UserDto> findByCondition(UserDto userDto, Pageable pageable) {
        User user = userMapper.toEntity(userDto);
        Page<User> entityPage = repository.findAll(pageable);
        List<User> entities = entityPage.getContent();
        return new PageImpl<>(userMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public UserDto update(UserDto userDto, Long id) {
        UserDto data = findById(id);
        User entity = userMapper.toEntity(userDto);
        BeanUtil.copyProperties(data, entity);
        return save(userMapper.toDto(entity));
    }
}