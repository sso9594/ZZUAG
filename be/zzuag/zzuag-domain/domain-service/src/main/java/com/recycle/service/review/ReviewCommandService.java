package com.recycle.service.review;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewCommandService {
    private final ReviewRdsCommandService reviewRdsCommandService;

    public void createReview(Review review){
        reviewRdsCommandService.createReview(review);
    }

    public void updateReview(Review review){
        reviewRdsCommandService.updateReview(review);
    }

    public void deleteReview(Review review){
        reviewRdsCommandService.deleteReview(review);
    }
}
