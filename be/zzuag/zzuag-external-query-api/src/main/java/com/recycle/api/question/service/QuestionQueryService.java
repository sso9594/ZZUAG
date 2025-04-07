package com.recycle.api.question.service;


import com.recycle.api.question.cache.QuestionSearchCacheFacade;
import com.recycle.api.question.dto.response.QuestionByUserResponse;
import com.recycle.api.question.dto.response.QuestionResponse;
import com.recycle.api.question.util.QuestionResponseMapper;
import com.recycle.common.annotation.LoggingCache;
import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.domain.question.dto.CachedQuestionResponse;
import com.recycle.domain.question.dto.QuestionRdsResponse;
import com.recycle.domain.question.entity.Question;
import com.recycle.service.question.service.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionQueryDomainService questionQueryDomainService;
    private final QuestionSearchCacheFacade questionSearchCacheFacade;

    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long questionId) {
        Question result = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        return QuestionResponse.convert(result);
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> findQuestionsByTop10Reviewed() {
        return questionQueryDomainService.findQuestionsByTop10Reviewed()
                .stream()
                .map(QuestionResponse::convert)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<QuestionByUserResponse> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<QuestionByUserResponse> result = questionQueryDomainService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable)
                .stream()
                .map(QuestionByUserResponse::convert)
                .toList();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Transactional(readOnly = true)
    public Page<QuestionResponse> findUserInterestedQuestions(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<QuestionResponse> result = questionQueryDomainService.findUserInterestedQuestions(userId, pageable)
                .stream()
                .map(QuestionResponse::convert)
                .toList();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestionsByUserId(Long userId) {
        return questionQueryDomainService.getQuestionsByUserId(userId)
                .stream()
                .map(QuestionResponse::convert)
                .toList();
    }

//    @Transactional(readOnly = true)
//    @Deprecated
//    public Page<QuestionResponse> findQuestionsByKeyword(String keyword, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        List<QuestionResponse> result = questionQueryDomainService.findQuestionsByKeyword(keyword, pageable)
//                .stream()
//                .map(QuestionResponse::convert)
//                .toList();
//        return new PageImpl<>(result, pageable, result.size());
//    }

    @LoggingCache
    public Page<QuestionResponse> searchQuestions(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Optional<CachedQuestionPage> cache = questionSearchCacheFacade.findQuestionsByKeyword(keyword, page);

        if (cache.isPresent()) {
            List<QuestionResponse> responses = cache.get().getContent().stream()
                    .map(QuestionResponse::fromCached)
                    .toList();

            return new PageImpl<>(responses, pageable, cache.get().getTotalElements());
        }

        Page<QuestionRdsResponse> result = questionQueryDomainService.findQuestionsFromRds(keyword, pageable);

        List<CachedQuestionResponse> toCache = result.getContent().stream()
                .map(QuestionResponseMapper::toCached)
                .toList();

        CachedQuestionPage cached = CachedQuestionPage.builder()
                .content(toCache)
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(result.getNumber())
                .build();

        questionQueryDomainService.cachePage(keyword, page, cached);

        return result.map(QuestionResponse::fromRds);
    }

}
