package com.zzuag.post_command_service.domain.question.dto.request;

public record QuestionLikeCreateRequest(
        Long questionId,
        Long userId) {
}
