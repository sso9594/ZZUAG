package com.recycle.service.review.service;

import com.recycle.domain.review.entity.ReviewFavorite;
import com.recycle.domain.review.service.ReviewFavoriteRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewFavoriteCommandDomainService {
    private final ReviewFavoriteRdsCommandService reviewFavoriteRdsCommandService;

    public void createReviewFavorite(ReviewFavorite reviewFavorite) {
        reviewFavoriteRdsCommandService.createReviewFavorite(reviewFavorite);
    }

    public void deleteReviewFavorite(Long reviewId, Long userId) {
        reviewFavoriteRdsCommandService.deleteReviewFavorite(reviewId, userId);
    }
}
