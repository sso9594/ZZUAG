package com.recycle.api.question.dto.response;

import com.recycle.domain.question.entity.Question;
import lombok.Builder;

@Builder
public record QuestionResponse(
        Long questionId,
        String title,
        String content,
        Long userId,
        int likeCount,
        int reviewCount
) {
    public static QuestionResponse convert(Question question) {
        return QuestionResponse.builder()
                .questionId(question.getId())
                .title(question.getMetaData().getTitle())
                .content(question.getContent())
                .userId(question.getUserId())
                .likeCount(question.getLikeCount())
                .reviewCount(question.getMetaData().getReviewCount())
                .build();
    }
}
