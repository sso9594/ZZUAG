package com.recycle.domain.review.repository;

import com.recycle.domain.review.dto.CachedReviewLikePage;

import java.util.Optional;

public interface ReviewCacheRepository {
    public Optional<CachedReviewLikePage> getCachedReviewPage(Long userId, int page, int size);
    public void setCachedReviewPage(Long userId, int page, int size, CachedReviewLikePage cachedReviewLikePage);
}
