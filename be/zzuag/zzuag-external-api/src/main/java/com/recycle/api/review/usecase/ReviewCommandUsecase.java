package com.recycle.api.review.usecase;

import com.recycle.api.review.dto.request.ReviewCreateRequest;
import com.recycle.api.review.service.ReviewCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewCommandUsecase {
    private final ReviewCommandService reviewCommandService;

    public void createReview(Long userId, Long questionId, ReviewCreateRequest request) {
        reviewCommandService.createReview(userId, questionId, request);
    }

    public void updateReview(Long userId, Long reviewId, ReviewCreateRequest request) {
        reviewCommandService.updateReview(userId, reviewId, request);
    }

    public void deleteReview(Long userId, Long reviewId) {
        reviewCommandService.deleteReview(userId, reviewId);
    }
}
