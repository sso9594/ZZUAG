package com.recycle.domain.question.dto;

public record QuestionWithReviewLikesByUserDTO(
        Long questionId,
        String content,
        Long questionLikeCnt,
        Long totalReviewLikes,
        Long reviewCount
) {
}
