package com.recycle.domain.review.service;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewRdsCommandService {
    private final ReviewRepository reviewRepository;

    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    public void updateReview(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReview(Review review) {
        review.delete();
    }
}
