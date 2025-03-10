package com.recycle.domain.review.service;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewRdsCommandService {
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(Review review) {
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Review review) {
        review.delete();
    }
}
