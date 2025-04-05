package com.recycle.service.review.dto;

import lombok.Builder;

@Builder
public record ReviewWithReviewLikesByUserDTO(
        Long reviewId,
        String questionPreview,
        String content,
        int reviewLikeCnt,
        int totalReviewLikes
) {
}
