package com.recycle.domain.review.service;

import com.recycle.domain.review.entity.ReviewFavorite;
import com.recycle.domain.review.repository.ReviewFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFavoriteRdsCommandService {
    private final ReviewFavoriteRepository reviewFavoriteRepository;

    public void createReviewFavorite(ReviewFavorite reviewFavorite) {
        reviewFavoriteRepository.save(reviewFavorite);
    }

    public void deleteReviewFavorite(Long reviewId, Long userId) {
        reviewFavoriteRepository.deleteByReviewIdAndUserId(reviewId, userId);
    }
}
