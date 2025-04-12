package com.recycle.domain.question.dto;

import lombok.Builder;
import java.util.List;

@Builder
public record QuestionIdResult(
        List<Long> ids,
        long totalCount
) {}

