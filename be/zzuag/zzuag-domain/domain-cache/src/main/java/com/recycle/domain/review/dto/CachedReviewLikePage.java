package com.recycle.domain.review.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CachedReviewLikePage {
    List<CachedReviewLikesByUser> content;
    int totalPages;
    long totalElements;
    int currentPage;
}
