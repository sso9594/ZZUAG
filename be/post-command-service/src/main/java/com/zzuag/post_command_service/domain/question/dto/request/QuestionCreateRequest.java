package com.zzuag.post_command_service.domain.question.dto.request;

public record QuestionCreateRequest(
        String title,
        String content
) {
}
