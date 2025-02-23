package com.zzuag.post_command_service.domain.question.controller;

import com.zzuag.common_module.aop.AuthenticationAspect;
import com.zzuag.common_module.aop.annotation.PassportAuth;
import com.zzuag.common_module.passport.Passport;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionCreateRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionEditRequest;
import com.zzuag.post_command_service.domain.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @PassportAuth
    public ResponseEntity<Void> createQuestion(@RequestBody @Valid QuestionCreateRequest request) {
        Passport passport = AuthenticationAspect.getPassport();
        questionService.createQuestion(passport.userId(), request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{questionId}")
    @PassportAuth
    public ResponseEntity<Void> editQuestion(@PathVariable Long questionId , @RequestBody @Valid QuestionEditRequest request) {
        Passport passport = AuthenticationAspect.getPassport();
        questionService.editQuestion(questionId, passport.userId(), request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{questionId}")
    @PassportAuth
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        Passport passport = AuthenticationAspect.getPassport();
        questionService.deleteQuestion(passport.userId(), questionId);
        return ResponseEntity.ok().build();
    }
}
