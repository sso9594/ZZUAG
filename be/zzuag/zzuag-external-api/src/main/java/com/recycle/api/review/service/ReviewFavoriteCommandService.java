package com.recycle.api.review.service;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.entity.ReviewFavorite;
import com.recycle.domain.review.exception.ReviewErrCode;
import com.recycle.domain.review.exception.exceptions.NoSuchReviewException;
import com.recycle.service.review.service.ReviewFavoriteCommandDomainService;
import com.recycle.service.review.service.ReviewQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewFavoriteCommandService {
    private final ReviewFavoriteCommandDomainService reviewFavoriteCommandDomainService;
    private final ReviewQueryDomainService reviewQueryDomainService;

    public void createReviewFavorite(Long userId, Long reviewId) {
        Review target = reviewQueryDomainService.getReviewById(reviewId)
                .orElseThrow(() -> new NoSuchReviewException(ReviewErrCode.NO_SUCH_REVIEW));
        reviewFavoriteCommandDomainService.createReviewFavorite(
                ReviewFavorite.create(
                        userId,
                        target
                )
        );
    }

    public void deleteReviewFavorite(Long userId, Long reviewId) {
        reviewFavoriteCommandDomainService.deleteReviewFavorite(userId, reviewId);
    }
}
