package com.recycle.api.question.dto.response;

import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import lombok.Builder;

@Builder
public record QuestionByUserResponse(
        Long questionId,
        String content,
        int questionLikeCnt,
        int totalReviewLikes,
        int reviewCount
) {
    public static QuestionByUserResponse convert(QuestionWithReviewLikesByUserDTO dto) {
        return QuestionByUserResponse.builder()
                .questionId(dto.questionId())
                .content(dto.content())
                .questionLikeCnt(dto.questionLikeCnt())
                .totalReviewLikes(dto.totalReviewLikes())
                .reviewCount(dto.reviewCount())
                .build();
    }
}
