package com.recycle.api.question.dto.response;

import com.recycle.domain.question.entity.Question;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record QuestionResponse(
        Long questionId,
        String title,
        String content,
        Long userId,
        int likeCount,
        int reviewCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static QuestionResponse convert(Question question) {
        return QuestionResponse.builder()
                .questionId(question.getId())
                .title(question.getMetaData().getTitle())
                .content(question.getContent())
                .userId(question.getUserId())
                .likeCount(question.getLikeCount())
                .reviewCount(question.getMetaData().getReviewCount())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build();
    }
}
