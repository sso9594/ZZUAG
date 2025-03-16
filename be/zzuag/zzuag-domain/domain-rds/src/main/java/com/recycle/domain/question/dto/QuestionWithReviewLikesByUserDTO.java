package com.recycle.domain.question.dto;

public record QuestionWithReviewLikesByUserDTO(
        Long questionId,
        String title,
        String content,
        int questionLikeCnt,
        int totalReviewLikes,
        int reviewCount
) {
}
