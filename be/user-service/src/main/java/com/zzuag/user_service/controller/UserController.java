package com.zzuag.user_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzuag.common_module.aop.AuthenticationAspect;
import com.zzuag.common_module.aop.annotation.PassportAuth;
import com.zzuag.common_module.passport.Passport;
import com.zzuag.user_service.dto.request.EditProfileRequest;
import com.zzuag.user_service.dto.request.SignupRequest;
import com.zzuag.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody @Valid SignupRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/profile")
    @PassportAuth
    public ResponseEntity<Void> editUser(@RequestBody @Valid EditProfileRequest request) throws JsonProcessingException {
        Passport passport = AuthenticationAspect.getPassport();
        userService.editProfile(passport, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    @PassportAuth
    public ResponseEntity<Void> viewUserProfile() throws JsonProcessingException {
        Passport passport = AuthenticationAspect.getPassport();
        userService.viewUserProfile(passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/resign")
    @PassportAuth
    public ResponseEntity<Void> resignUser() throws JsonProcessingException {
        Passport passport = AuthenticationAspect.getPassport();
        userService.resignUser(passport);
        return ResponseEntity.ok().build();
    }
}
