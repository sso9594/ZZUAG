package com.recycle.api.review.service;

import com.recycle.api.question.dto.response.ReviewPreviewDTO;
import com.recycle.api.review.dto.response.ReviewByUserResponse;
import com.recycle.api.review.dto.response.ReviewResponse;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.exception.ReviewErrCode;
import com.recycle.domain.review.exception.exceptions.NoSuchReviewException;
import com.recycle.service.review.service.ReviewQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewQueryDomainService reviewQueryDomainService;

    @Transactional(readOnly = true)
    public ReviewResponse getReviewById(Long reviewId) {
        Review result = reviewQueryDomainService.getReviewById(reviewId)
                .orElseThrow(() -> new NoSuchReviewException(ReviewErrCode.NO_SUCH_REVIEW));
        return ReviewResponse.convert(result);
    }

    @Transactional(readOnly = true)
    public Page<ReviewByUserResponse> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ReviewByUserResponse> result = reviewQueryDomainService.getReviewsByUserIdAndTopLikeCountByPagination(userId, pageable)
                .stream()
                .map(ReviewByUserResponse::convert)
                .toList();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Transactional(readOnly = true)
    public List<ReviewPreviewDTO> getTopReviewedDTOs() {
        return reviewQueryDomainService.getTopReviewedDTOs()
                .stream()
                .map(ReviewPreviewDTO::convert)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByQuestionId(Long questionId) {
        return reviewQueryDomainService.getReviewsByQuestionId(questionId)
                .stream()
                .map(ReviewResponse::convert)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        return reviewQueryDomainService.getReviewsByUserId(userId)
                .stream()
                .map(ReviewResponse::convert)
                .toList();
    }
}
