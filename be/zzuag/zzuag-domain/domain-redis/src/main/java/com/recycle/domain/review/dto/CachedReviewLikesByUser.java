package com.recycle.domain.review.dto;

import lombok.Builder;

@Builder
public record CachedReviewLikesByUser(
        Long reviewId,
        String questionPreview,
        String content,
        int reviewLikeCnt,
        int totalReviewLikes
) {
}
