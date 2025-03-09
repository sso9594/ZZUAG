package com.recycle.service.review;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewQueryService {
    private final ReviewRdsQueryService reviewRdsQueryService;

    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRdsQueryService.getReviewById(reviewId);
    }
}
