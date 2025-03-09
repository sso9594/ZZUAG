package com.recycle.api.review.controller;

import com.recycle.api.review.api.ReviewApi;
import com.recycle.api.review.dto.ReviewCreateRequest;
import com.recycle.api.review.usecase.ReviewUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class ReviewController implements ReviewApi {
    private final ReviewUsecase reviewUsecase;

    @PostMapping("/{questionId}/review")
    public ResponseEntity<Void> createReview(@PathVariable Long questionId, @RequestBody @Valid ReviewCreateRequest request) {
        Long userId = 1L;
        reviewUsecase.createReview(userId, questionId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{questionId}/review/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId, @RequestBody @Valid ReviewCreateRequest request) {
        Long userId = 1L;
        reviewUsecase.updateReview(userId, reviewId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}/review/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        Long userId = 1L;
        reviewUsecase.deleteReview(userId, reviewId);
        return ResponseEntity.ok().build();
    }
}
