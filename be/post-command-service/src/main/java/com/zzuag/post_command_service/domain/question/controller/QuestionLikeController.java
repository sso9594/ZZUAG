package com.zzuag.post_command_service.domain.question.controller;

import com.zzuag.common_module.aop.AuthenticationAspect;
import com.zzuag.common_module.aop.annotation.PassportAuth;
import com.zzuag.common_module.passport.Passport;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionLikeRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionUnlikeRequest;
import com.zzuag.post_command_service.domain.question.service.QuestionLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/api/v1/question/likes")
@RequiredArgsConstructor
@Validated
public class QuestionLikeController {
    private final QuestionLikeService questionLikeService;

    // 인증 관련 로직 + aop 적용 필요
    @GetMapping("/like")
    @PassportAuth
    public ResponseEntity<Void> likeQuestion(QuestionLikeRequest request) {
        Passport passport = AuthenticationAspect.getPassport();
        questionLikeService.likeQuestion(passport.userId(), request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unlike")
    @PassportAuth
    public ResponseEntity<Void> unlikeQuestion(QuestionUnlikeRequest request) {
        Passport passport = AuthenticationAspect.getPassport();
        questionLikeService.unlikeQuestion(passport.userId(), request);
        return ResponseEntity.ok().build();
    }
}
