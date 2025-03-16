package com.recycle.api.review.dto.response;

import com.recycle.domain.review.dto.ReviewWithReviewLikesByUserDTO;
import lombok.Builder;

@Builder
public record ReviewByUserResponse(
        Long reviewId,
        String questionPreview,
        String content,
        int reviewLikeCnt,
        int totalReviewLikes
) {
    public static ReviewByUserResponse convert(ReviewWithReviewLikesByUserDTO dto) {
        return ReviewByUserResponse.builder()
                .reviewId(dto.reviewId())
                .questionPreview(dto.questionPreview())
                .content(dto.content())
                .reviewLikeCnt(dto.reviewLikeCnt())
                .totalReviewLikes(dto.totalReviewLikes())
                .build();
    }
}
