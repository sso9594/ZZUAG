package com.recycle.api.question.dto.response;

import com.recycle.domain.review.dto.TopReviewedDTO;

public record ReviewPreviewDTO(
        Long reviewId,
        // 리뷰 된 질문의 20자 미리보기
        String questionPreview,
        String content,
        int reviewLikeCnt
) {
    public static ReviewPreviewDTO convert(TopReviewedDTO dto) {
        return new ReviewPreviewDTO(
                dto.reviewId(),
                dto.questionPreview(),
                dto.content(),
                dto.reviewLikeCnt()
        );
    }
}
