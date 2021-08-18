package com.nhom43.quanlychungcubackendgradle.share.service;

import com.nhom43.quanlychungcubackendgradle.entity.User;
import com.nhom43.quanlychungcubackendgradle.ex.SpringException;
import com.nhom43.quanlychungcubackendgradle.repository.UserRepository;
import com.nhom43.quanlychungcubackendgradle.share.dto.request.ProfileRequest;
import com.nhom43.quanlychungcubackendgradle.share.dto.response.ProfileResponse;
import com.nhom43.quanlychungcubackendgradle.share.mapper.ProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final ProfileMapper profileMapper;


    @Transactional(readOnly = true)
    public String checkUser() {
        User user = authService.getCurrentUser();

        switch (user.getRole()) {
            case "User":
                return "User";
            case "Admin":
                return "Admin";
            case "Staff":
                return "Staff";
        }
        return "null";
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfile() {
        User user = authService.getCurrentUser();
        return profileMapper.mapToDtoByUserName(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfileId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new SpringException("Không có tài khoản nào có ID: " + id));
        return profileMapper.mapToDtoByUserName(user);
    }

    public void update(ProfileRequest profileRequest) {
        User user = authService.getCurrentUser();
        userRepository.save(profileMapper.mapUpdateToDtoById(profileRequest, user));
    }

    public void edit(ProfileRequest profileRequest) {
        Optional<User> email = userRepository.findByEmail(profileRequest.getEmail());
        if (email.isPresent()) {
            userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        } else {
            if (email.get().getEmail().equals(profileRequest.getEmail()) && email.get().getId().equals(profileRequest.getId())) {
                userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
            } else {
                throw new SpringException("Email đã tồn tại");
            }
        }
    }

    public void editPassword(ProfileRequest profileRequest) {
        if (passwordEncoder.matches(profileRequest.getPassword(), authService.getCurrentUser().getPassword())) {
            User user = authService.getCurrentUser();
            user.setPassword(passwordEncoder.encode(profileRequest.getPasswordEdit()));
            userRepository.save(user);
        } else {
            throw new SpringException("Mật khẩu cũ không khớp");
        }

    }
}
