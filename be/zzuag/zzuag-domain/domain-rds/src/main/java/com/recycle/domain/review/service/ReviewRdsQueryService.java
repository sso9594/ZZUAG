package com.recycle.domain.review.service;

import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserRdsDTO;
import com.recycle.domain.review.dto.TopReviewedDTO;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewRdsQueryService {
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findByIdAndIsDeletedFalse(reviewId);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByQuestionId(Long questionId) {
        return reviewRepository.findByMetaData_Question_IdAndIsDeletedFalse(questionId);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserIdAndIsDeletedFalse(userId);
    }

    @Transactional(readOnly = true)
    public Page<ReviewWithReviewLikesByUserRdsDTO> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return reviewRepository.getReviewsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<TopReviewedDTO> getTopReviewedDTOs() {
        return reviewRepository.getTopReviewedDTOs();
    }
}
