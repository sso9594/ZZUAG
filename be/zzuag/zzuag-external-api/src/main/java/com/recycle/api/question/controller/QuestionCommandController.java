package com.recycle.api.question.controller;

import com.recycle.api.question.api.QuestionCommandApi;
import com.recycle.api.question.dto.request.QuestionCreateRequest;
import com.recycle.api.question.dto.request.QuestionUpdateRequest;
import com.recycle.api.question.usecase.QuestionCommandUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionCommandController implements QuestionCommandApi {
    private final QuestionCommandUsecase questionCommandUsecase;

    @PostMapping
    public ResponseEntity<Void> createQuestion(@RequestBody @Valid QuestionCreateRequest request) {
        Long userId = 1L;
        questionCommandUsecase.createQuestion(userId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Void> updateQuestion(@PathVariable Long questionId , @RequestBody @Valid QuestionUpdateRequest request) {
        Long userId = 1L;
        questionCommandUsecase.updateQuestion(userId, questionId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        Long userId = 1L;
        questionCommandUsecase.deleteQuestion(userId, questionId);
        return ResponseEntity.ok().build();
    }
}
