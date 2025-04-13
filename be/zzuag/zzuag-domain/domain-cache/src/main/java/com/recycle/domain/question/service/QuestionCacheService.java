package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.domain.question.repository.QuestionCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionCacheService {
    private final QuestionCacheRepository questionCacheRepository;

    public Optional<CachedQuestionPage> getCachedQuestionPage(String keyword, int page, int size) {
        return questionCacheRepository.getCachedQuestionPage(keyword, page , size);
    }

    public void setCachedQuestionResponse(String keyword, int page, int size, CachedQuestionPage cachedQuestionPage) {
        log.info("[PUT CACHE] service layer response");
        questionCacheRepository.setCachedQuestionResponse(keyword, page, size, cachedQuestionPage);
    }
}
