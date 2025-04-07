package com.recycle.api.review.dto.response;

import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.entity.ReviewPoint;
import com.recycle.domain.review.entity.ReviewType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewResponse(
        Long reviewId,
        String content,
        Long userId,
        ReviewPoint startPoint,
        ReviewPoint endPoint,
        ReviewType tag,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ReviewResponse convert(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .userId(review.getUserId())
                .startPoint(review.getMetaData().getStartPoint())
                .endPoint(review.getMetaData().getEndPoint())
                .tag(review.getMetaData().getTag())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
