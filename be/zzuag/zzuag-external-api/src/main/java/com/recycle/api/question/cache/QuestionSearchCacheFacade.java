package com.recycle.api.question.cache;

import com.recycle.common.annotation.LoggingCache;
import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.service.question.service.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionSearchCacheFacade {

    private final QuestionQueryDomainService questionQueryDomainService;

    @LoggingCache
    public Optional<CachedQuestionPage> findQuestionsByKeyword(String keyword, int page) {
        return questionQueryDomainService.getCachedPage(keyword, page);
    }

}
