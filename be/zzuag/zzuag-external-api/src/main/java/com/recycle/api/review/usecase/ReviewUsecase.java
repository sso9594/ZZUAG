package com.recycle.api.review.usecase;

import com.recycle.api.review.dto.ReviewCreateRequest;
import com.recycle.api.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewUsecase {
    private final ReviewService reviewService;

    public void createReview(Long userId, Long questionId, ReviewCreateRequest request) {
        reviewService.createReview(userId, questionId, request);
    }

    public void updateReview(Long userId, Long reviewId, ReviewCreateRequest request) {
        reviewService.updateReview(userId, reviewId, request);
    }

    public void deleteReview(Long userId, Long reviewId) {
        reviewService.deleteReview(userId, reviewId);
    }
}
