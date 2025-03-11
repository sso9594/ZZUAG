package com.recycle.service.review;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewQueryService {
    private final ReviewRdsQueryService reviewRdsQueryService;

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRdsQueryService.getReviewById(reviewId);
    }
}
