package com.recycle.domain.review.service;

import com.recycle.domain.review.dto.CachedReviewLikePage;
import com.recycle.domain.review.repository.ReviewCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewCacheService {
    private final ReviewCacheRepository reviewCacheRepository;

    public Optional<CachedReviewLikePage> getCachedReviewPage(Long userId, int page, int size) {
        return reviewCacheRepository.getCachedReviewPage(userId, page, size);
    }

    public void setCachedReviewPage(Long userId, int page, int size, CachedReviewLikePage cachedReviewLikePage) {
        reviewCacheRepository.setCachedReviewPage(userId, page, size, cachedReviewLikePage);
    }
}
