package com.recycle.api.review.dto;

import com.recycle.domain.review.entity.ReviewPoint;
import com.recycle.domain.review.entity.ReviewType;

public record ReviewCreateRequest(
        String content,
        ReviewPoint startPoint,
        ReviewPoint endPoint,
        ReviewType tag
) {
}
