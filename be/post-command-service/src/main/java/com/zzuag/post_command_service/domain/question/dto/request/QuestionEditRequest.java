package com.zzuag.post_command_service.domain.question.dto.request;

public record QuestionEditRequest(
        String title,
        String content
) {
}
