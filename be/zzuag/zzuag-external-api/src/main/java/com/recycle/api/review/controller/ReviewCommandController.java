package com.recycle.api.review.controller;

import com.recycle.api.review.api.ReviewCommandApi;
import com.recycle.api.review.dto.request.ReviewCreateRequest;
import com.recycle.api.review.usecase.ReviewCommandUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class ReviewCommandController implements ReviewCommandApi {
    private final ReviewCommandUsecase reviewCommandUsecase;

    @PostMapping("/{questionId}/review")
    public ResponseEntity<Void> createReview(@PathVariable Long questionId, @RequestBody @Valid ReviewCreateRequest request) {
        Long userId = 1L;
        reviewCommandUsecase.createReview(userId, questionId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{questionId}/review/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId, @RequestBody @Valid ReviewCreateRequest request) {
        Long userId = 1L;
        reviewCommandUsecase.updateReview(userId, reviewId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        Long userId = 1L;
        reviewCommandUsecase.deleteReview(userId, reviewId);
        return ResponseEntity.ok().build();
    }
}
