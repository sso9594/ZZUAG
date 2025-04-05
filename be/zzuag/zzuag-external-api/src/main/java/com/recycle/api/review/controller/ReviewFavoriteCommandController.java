package com.recycle.api.review.controller;

import com.recycle.api.review.api.ReviewFavoriteCommandApi;
import com.recycle.api.review.usecase.ReviewFavoriteCommandUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/api/v1/favorite/review")
@RequiredArgsConstructor
@Validated
public class ReviewFavoriteCommandController implements ReviewFavoriteCommandApi {
    private final ReviewFavoriteCommandUsecase reviewFavoriteCommandUsecase;

    @PostMapping("/{reviewId}")
    public ResponseEntity<Void> createReviewFavorite(Long reviewId) {
        Long userId = 1L;
        reviewFavoriteCommandUsecase.createReviewFavorite(userId, reviewId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReviewFavorite(Long reviewId) {
        Long userId = 1L;
        reviewFavoriteCommandUsecase.deleteReviewFavorite(userId, reviewId);
        return ResponseEntity.ok().build();
    }

}
