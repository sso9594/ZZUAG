package com.recycle.domain.review.repository;

import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserRdsDTO;
import com.recycle.domain.review.dto.TopReviewedDTO;
import com.recycle.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewCustomRepository {
    List<TopReviewedDTO> getTopReviewedDTOs();
    Page<ReviewWithReviewLikesByUserRdsDTO> getReviewsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable);
    Page<Review> findUserInterestedReviews(Long userId, Pageable pageable);
}
