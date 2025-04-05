package com.recycle.domain.question.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CachedQuestionResponse(
        Long questionId,
        String title,
        String content,
        Long userId,
        int likeCount,
        int reviewCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
