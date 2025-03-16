package com.recycle.domain.review.dto;

public record ReviewWithReviewLikesByUserDTO(
        Long reviewId,
        String questionPreview,
        String content,
        int reviewLikeCnt,
        int totalReviewLikes
) {
}
