package com.recycle.api.review.usecase;

import com.recycle.api.question.dto.response.ReviewPreviewDTO;
import com.recycle.api.review.dto.response.ReviewByUserResponse;
import com.recycle.api.review.dto.response.ReviewResponse;
import com.recycle.api.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewQueryUsecase {
    private final ReviewQueryService reviewQueryService;

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long reviewId) {
        return reviewQueryService.getReviewById(reviewId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewByUserResponse> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        return reviewQueryService.getReviewsByUserIdAndTopLikeCountByPagination(userId, page, size);
    }

    @Transactional(readOnly = true)
    public List<ReviewPreviewDTO> getTopReviewedDTOs() {
        return reviewQueryService.getTopReviewedDTOs();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByQuestionId(Long questionId) {
        return reviewQueryService.getReviewsByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        return reviewQueryService.getReviewsByUserId(userId);
    }
}
