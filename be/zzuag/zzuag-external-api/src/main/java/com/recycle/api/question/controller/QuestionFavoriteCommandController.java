package com.recycle.api.question.controller;

import com.recycle.api.question.api.QuestionFavoriteCommandApi;
import com.recycle.api.question.usecase.QuestionFavoriteCommandUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/favorite/question")
@RequiredArgsConstructor
@Validated
public class QuestionFavoriteCommandController implements QuestionFavoriteCommandApi {
    private final QuestionFavoriteCommandUsecase questionFavoriteCommandUsecase;

    @PostMapping("/{questionId}")
    public ResponseEntity<Void> createQuestionFavorite (@PathVariable @Valid Long questionId) {
        Long userId = 1L;
        questionFavoriteCommandUsecase.createQuestionFavorite(userId, questionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestionFavorite (@PathVariable @Valid Long questionId) {
        Long userId = 1L;
        questionFavoriteCommandUsecase.deleteQuestionFavorite(userId, questionId);
        return ResponseEntity.ok().build();
    }
}
