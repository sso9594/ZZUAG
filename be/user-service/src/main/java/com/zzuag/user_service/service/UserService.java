package com.zzuag.user_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzuag.user_service.dto.request.EditProfileRequest;
import com.zzuag.user_service.dto.request.SignupRequest;
import com.zzuag.user_service.dto.response.UserInfoResponse;
import com.zzuag.user_service.entity.User;
import com.zzuag.user_service.persistence.UserRepository;
import com.zzuag.user_service.util.UserInfoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void checkEmail(String email) {
        if (userRepository.existsByUserCertification_Email(email)) {
            throw new IllegalArgumentException ("Email already exists");
        }
    }

    public void signUp(SignupRequest request) {
        checkEmail(request.userCertification().getEmail());
        User user = User.signUp(
                request.userCertification().getEmail(),
                request.userCertification().getPassword(),
                request.userInfo(),
                request.userRole()
        );
        userRepository.save(user);

    }

    // todo: common-module에서 aop 완성하고 passport 가져와서 userInfo 채우기
    public void editProfile(EditProfileRequest request) throws JsonProcessingException {
        User targetUser = userRepository.findByUserIdAndIsDeletedIsFalse(userInfo.userId())
                .orElseThrow(() -> new NoSuchElementException("Email Not exists"));

        targetUser.updateUserInfo(request.userInfo());

    }

    public UserInfoResponse viewUserProfile(String passport) throws JsonProcessingException {
        User targetUser = userRepository.findByUserIdAndIsDeletedIsFalse(userInfo.userId())
                .orElseThrow(() -> new NoSuchElementException("Email Not exists"));
        return UserInfoConverter.from(targetUser);
    }

    public void resignUser(String token) throws JsonProcessingException {
        User targetUser = userRepository.findByUserIdAndIsDeletedIsFalse(userInfo.userId())
                .orElseThrow(() -> new NoSuchElementException("Email Not exists"));
        targetUser.delete();
    }

}
