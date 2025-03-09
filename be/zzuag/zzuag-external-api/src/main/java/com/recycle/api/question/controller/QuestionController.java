package com.recycle.api.question.controller;

import com.recycle.api.question.api.QuestionApi;
import com.recycle.api.question.dto.QuestionCreateRequest;
import com.recycle.api.question.dto.QuestionUpdateRequest;
import com.recycle.api.question.usecase.QuestionUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionController implements QuestionApi {
    private final QuestionUsecase questionUsecase;

    @PostMapping
    public ResponseEntity<Void> createQuestion(@RequestBody @Valid QuestionCreateRequest request) {
        Long userId = 1L;
        questionUsecase.createQuestion(userId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long questionId , @RequestBody @Valid QuestionUpdateRequest request) {
        Long userId = 1L;
        questionUsecase.updateQuestion(userId, questionId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        Long userId = 1L;
        questionUsecase.deleteQuestion(userId, questionId);
        return ResponseEntity.ok().build();
    }
}
