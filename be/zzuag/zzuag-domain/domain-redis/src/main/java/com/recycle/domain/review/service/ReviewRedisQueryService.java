package com.recycle.domain.review.service;

import com.recycle.domain.review.dto.CachedReviewLikePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReviewRedisQueryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CACHED_REVIEW_KEY = "review:byUser:";

    public Optional<CachedReviewLikePage> getCachedReviewPage(Long userId, int page) {
        String key = CACHED_REVIEW_KEY + userId + ":" + page;
        return Optional.ofNullable((CachedReviewLikePage) redisTemplate.opsForValue().get(key));

    }

    public void setCachedReviewPage(Long userId, int page, CachedReviewLikePage cachedReviewLikePage) {
        String key = CACHED_REVIEW_KEY + userId + ":" + page;
        redisTemplate.opsForValue().set(key, cachedReviewLikePage);
    }

    public void evictCachedReviewPage(Long userId, int page) {
        Set<String> keys = redisTemplate.keys(CACHED_REVIEW_KEY + userId + ":*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
