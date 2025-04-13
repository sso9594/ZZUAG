package com.recycle.domain.review.repository;

import com.recycle.common.util.Cache;
import com.recycle.domain.review.dto.CachedReviewLikePage;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReviewCacheRepositoryImpl implements ReviewCacheRepository{
    private static final Cache<String, CachedReviewLikePage> reviewCache = new Cache<>(5000);
    private static final String CACHED_REVIEW_KEY = "review:byUser:";

    public Optional<CachedReviewLikePage> getCachedReviewPage(Long userId, int page, int size) {
        String key = CACHED_REVIEW_KEY + userId + ":" + page + ":" + size;
        return Optional.ofNullable(
                reviewCache.get(key)
        );

    }

    public void setCachedReviewPage(Long userId, int page, int size, CachedReviewLikePage cachedReviewLikePage) {
        String key = CACHED_REVIEW_KEY + userId + ":" + page + ":" + size;
        reviewCache.put(key, cachedReviewLikePage);
    }

}
