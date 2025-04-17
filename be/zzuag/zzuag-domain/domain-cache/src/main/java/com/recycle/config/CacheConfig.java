package com.recycle.config;

import com.github.benmanes.caffeine.cache.Caffeine;

import com.recycle.common.util.Cache;
import com.recycle.common.util.CacheType;
import com.recycle.domain.question.dto.CachedQuestionPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.size}")
    private static int CACHE_SIZE;

    @Bean
    public List<CaffeineCache> caffeineCaches() {
        return Arrays.stream(CacheType.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cache.getExpiredAfterWrite(), TimeUnit.MINUTES)
                        .expireAfterAccess(cache.getExpiredAfterWrite(), TimeUnit.MINUTES)
                        .maximumSize(cache.getMaximumSize())
                        .build()))
                .toList();
    }
    @Bean
    public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }

    @Bean
    public Cache<String, CachedQuestionPage> questionCache() {
        return new Cache<>(CACHE_SIZE);
    }
}
