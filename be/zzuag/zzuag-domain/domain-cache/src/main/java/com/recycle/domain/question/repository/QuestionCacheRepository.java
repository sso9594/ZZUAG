package com.recycle.domain.question.repository;

import com.recycle.domain.question.dto.CachedQuestionPage;

import java.util.Optional;

public interface QuestionCacheRepository {
    public Optional<CachedQuestionPage> getCachedQuestionPage(String keyword, int page, int size);
    public void setCachedQuestionResponse(String keyword, int page, int size, CachedQuestionPage cachedQuestionPage);
}
