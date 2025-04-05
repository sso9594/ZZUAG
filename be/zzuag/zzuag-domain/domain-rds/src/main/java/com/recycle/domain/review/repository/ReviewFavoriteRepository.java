package com.recycle.domain.review.repository;

import com.recycle.domain.review.entity.ReviewFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFavoriteRepository extends JpaRepository<ReviewFavorite, Long> {
    void deleteByReviewIdAndUserId(Long reviewId, Long userId);
}
