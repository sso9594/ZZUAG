package com.recycle.domain.review.repository;

import com.recycle.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
    Optional<Review> findByIdAndIsDeletedFalse(Long reviewId);
    List<Review> findByMetaData_Question_IdAndIsDeletedFalse(Long questionId);
    List<Review> findByUserIdAndIsDeletedFalse(Long userId);
}
