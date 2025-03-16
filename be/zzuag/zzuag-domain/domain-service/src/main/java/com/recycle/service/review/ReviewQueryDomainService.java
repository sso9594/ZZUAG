package com.recycle.service.review;

import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserDTO;
import com.recycle.domain.review.dto.TopReviewedDTO;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.service.ReviewRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewQueryDomainService {
    private final ReviewRdsQueryService reviewRdsQueryService;

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRdsQueryService.getReviewById(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewWithReviewLikesByUserDTO> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return reviewRdsQueryService.getReviewsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<TopReviewedDTO> getTopReviewedDTOs() {
        return reviewRdsQueryService.getTopReviewedDTOs();
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByQuestionId(Long questionId) {
        return reviewRdsQueryService.getReviewsByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRdsQueryService.getReviewsByUserId(userId);
    }
}
