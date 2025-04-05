package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.domain.question.dto.CachedQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionRedisQueryService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String SEARCH_QUESTION_KEY = "search:question";

    public Optional<CachedQuestionPage> getCachedQuestionPage(String keyword, int page) {
        String key = SEARCH_QUESTION_KEY + keyword + ":" + page;
        return Optional.ofNullable(
                (CachedQuestionPage) redisTemplate.opsForValue().get(key)
        );
    }

    public void setCachedQuestionResponse(String keyword, int page, CachedQuestionPage cachedQuestionPage) {
        String key = SEARCH_QUESTION_KEY + keyword + ":" + page;
        redisTemplate.opsForValue().set(key, cachedQuestionPage);
    }
}
