package com.recycle.api.question.dto.request;

public record QuestionCreateRequest(
        String title,
        String content
) {
}
