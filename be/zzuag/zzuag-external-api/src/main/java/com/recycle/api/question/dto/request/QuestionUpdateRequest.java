package com.recycle.api.question.dto.request;

public record QuestionUpdateRequest(
        String title,
        String content
) {
}
