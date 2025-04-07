package com.recycle.api.question.util;

import com.recycle.domain.question.dto.CachedQuestionResponse;
import com.recycle.domain.question.dto.QuestionRdsResponse;

public class QuestionResponseMapper {
    public static CachedQuestionResponse toCached(QuestionRdsResponse r) {
        return CachedQuestionResponse.builder()
                .questionId(r.questionId())
                .title(r.title())
                .content(r.content())
                .userId(r.userId())
                .likeCount(r.likeCount())
                .reviewCount(r.reviewCount())
                .createdAt(r.createdAt())
                .updatedAt(r.updatedAt())
                .build();
    }
}
