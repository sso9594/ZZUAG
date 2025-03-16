package com.recycle.domain.review.dto;

public record TopReviewedDTO(
        Long reviewId,
        // 리뷰 된 질문의 20자 미리보기
        String questionPreview,
        String content,
        int reviewLikeCnt
) {
}
