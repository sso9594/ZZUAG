package com.recycle.domain.question.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CachedQuestionPage {
    private List<CachedQuestionResponse> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;

}
