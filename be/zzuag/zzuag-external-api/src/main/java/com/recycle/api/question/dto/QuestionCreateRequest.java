package com.recycle.api.question.dto;

public record QuestionCreateRequest(
        String title,
        String content
) {
}
