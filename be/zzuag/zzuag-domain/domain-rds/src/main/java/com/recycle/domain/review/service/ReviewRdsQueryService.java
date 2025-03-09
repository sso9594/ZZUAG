package com.recycle.domain.review.service;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewRdsQueryService {
    private final ReviewRepository reviewRepository;

    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findByIdAndIsDeletedFalse(reviewId);
    }
}
