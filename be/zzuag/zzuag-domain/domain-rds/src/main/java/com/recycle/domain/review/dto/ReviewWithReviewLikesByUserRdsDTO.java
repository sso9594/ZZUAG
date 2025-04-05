package com.recycle.domain.review.dto;

public record ReviewWithReviewLikesByUserRdsDTO(
        Long reviewId,
        String questionPreview,
        String content,
        int reviewLikeCnt,
        int totalReviewLikes
) {
}
