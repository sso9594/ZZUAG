package com.recycle.service.review;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewCommandService {
    private final ReviewRdsCommandService reviewRdsCommandService;

    @Transactional
    public void createReview(Review review){
        reviewRdsCommandService.createReview(review);
    }

    @Transactional
    public void updateReview(Review review){
        reviewRdsCommandService.updateReview(review);
    }

    @Transactional
    public void deleteReview(Review review){
        reviewRdsCommandService.deleteReview(review);
    }
}
