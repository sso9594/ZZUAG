package com.zzuag.post_command_service.domain.question.controller;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionCreateRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionEditRequest;
import com.zzuag.post_command_service.domain.question.service.QuestionLikeService;
import com.zzuag.post_command_service.domain.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// userId 는 인증 관련 로직 + aop 적용 필요
@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Void> createQuestion(@RequestBody @Valid QuestionCreateRequest request) {
        questionService.createQuestion(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{questionId}")
    public ResponseEntity<Void> editQuestion(@PathVariable Long questionId , @RequestBody @Valid QuestionEditRequest request) {
        questionService.editQuestion(questionId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok().build();
    }
}
