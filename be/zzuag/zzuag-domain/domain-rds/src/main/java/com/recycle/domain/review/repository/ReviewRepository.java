package com.recycle.domain.review.repository;

import com.recycle.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndIsDeletedFalse(Long reviewId);
}
