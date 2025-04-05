package com.recycle.api.review.usecase;

import com.recycle.api.review.service.ReviewFavoriteCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewFavoriteCommandUsecase {
    private final ReviewFavoriteCommandService reviewFavoriteCommandService;

    public void createReviewFavorite(Long userId, Long reviewId) {
        reviewFavoriteCommandService.createReviewFavorite(userId, reviewId);
    }

    public void deleteReviewFavorite(Long userId, Long reviewId) {
        reviewFavoriteCommandService.deleteReviewFavorite(userId, reviewId);
    }
}
