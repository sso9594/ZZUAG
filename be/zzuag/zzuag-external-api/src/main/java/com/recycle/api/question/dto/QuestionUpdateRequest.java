package com.recycle.api.question.dto;

public record QuestionUpdateRequest(
        String title,
        String content
) {
}
