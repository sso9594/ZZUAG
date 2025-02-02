package com.zzuag.post_command_service.domain.question.controller;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionLikeCreateRequest;
import com.zzuag.post_command_service.domain.question.service.QuestionLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/api/v1/question/like")
@RequiredArgsConstructor
@Validated
public class QuestionLikeController {
    private final QuestionLikeService questionLikeService;

    // 인증 관련 로직 + aop 적용 필요
    @GetMapping
    public ResponseEntity<Void> likeQuestion() {
        QuestionLikeCreateRequest request = null;
        questionLikeService.likeQuestion(request);
        return ResponseEntity.ok().build();
    }
}
