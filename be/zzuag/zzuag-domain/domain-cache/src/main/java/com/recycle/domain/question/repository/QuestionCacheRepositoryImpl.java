package com.recycle.domain.question.repository;

import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.common.util.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class QuestionCacheRepositoryImpl implements QuestionCacheRepository {
    private static final Cache<String, CachedQuestionPage> questionCache = new Cache<>(5000);

    private static final String SEARCH_QUESTION_KEY = "search:question";

    public Optional<CachedQuestionPage> getCachedQuestionPage(String keyword, int page, int size) {
        String key = SEARCH_QUESTION_KEY + keyword + ":" + size + ":" + page;
        return Optional.ofNullable(
                questionCache.get(key)
        );
    }

    public void setCachedQuestionResponse(String keyword, int page, int size, CachedQuestionPage cachedQuestionPage) {
        log.info("[PUT CACHE] repository layer response");
        String key = SEARCH_QUESTION_KEY + keyword + ":" + size + ":" + page;
        questionCache.put(key, cachedQuestionPage);
    }
}
