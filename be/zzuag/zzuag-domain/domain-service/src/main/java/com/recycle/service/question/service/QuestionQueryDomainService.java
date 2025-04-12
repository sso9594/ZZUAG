package com.recycle.service.question.service;

import com.recycle.domain.question.dto.QuestionIdResult;
import com.recycle.domain.question.service.QuestionEsQueryService;
import com.recycle.domain.question.dto.CachedQuestionPage;
import com.recycle.domain.question.dto.CachedQuestionResponse;
import com.recycle.domain.question.dto.QuestionRdsResponse;
import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.service.QuestionRdsQueryService;
import com.recycle.domain.question.service.QuestionRedisQueryService;
import com.recycle.service.question.dto.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionQueryDomainService {
    private final QuestionRdsQueryService questionRdsQueryService;
    private final QuestionRedisQueryService questionRedisQueryService;
    private final QuestionEsQueryService questionEsQueryService;

    @Transactional(readOnly = true)
    public Optional<Question> getQuestionById(Long questionId) {
        return questionRdsQueryService.getQuestionById(questionId);
    }

    @Transactional(readOnly = true)
    public List<Question> findQuestionsByTop10Reviewed() {
        return questionRdsQueryService.findQuestionsByTop10Reviewed();
    }

    @Transactional(readOnly = true)
    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return questionRdsQueryService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Question> findUserInterestedQuestions(Long userId, Pageable pageable) {
        return questionRdsQueryService.findUserInterestedQuestions(userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestionsByUserId(Long userId) {
        return questionRdsQueryService.getQuestionsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public QuestionIdResult findQuestionIdsByKeyword(String keyword, Pageable pageable) {
        return questionEsQueryService.findQuestionsByKeyword(keyword, pageable);
    }

    @Transactional(readOnly = true)
    public List<QuestionRdsResponse> findQuestionsByIds(List<Long> questionIds) {
        return questionRdsQueryService.findQuestionsByIds(questionIds);
    }

    public Optional<CachedQuestionPage> getCachedPage(String keyword, int page) {
        return questionRedisQueryService.getCachedQuestionPage(keyword, page);
    }

    public Page<QuestionRdsResponse> findQuestionsFromRds(String keyword, Pageable pageable) {
        return questionRdsQueryService.findQuestionsByKeyword(keyword, pageable);
    }

    public void cachePage(String keyword, int page, CachedQuestionPage cache) {
        questionRedisQueryService.setCachedQuestionResponse(keyword, page, cache);
    }

    @Transactional(readOnly = true)
    @Deprecated
    public Page<QuestionResponse> findQuestionsByKeyword(String keyword, Pageable pageable) {
        int page = pageable.getPageNumber();

        Optional<CachedQuestionPage> cache = questionRedisQueryService.getCachedQuestionPage(keyword, page);
        if(cache.isPresent()){
            CachedQuestionPage cachedQuestionPage = cache.get();
            List<CachedQuestionResponse> cachedQuestionResponses = cachedQuestionPage.getContent();
            List<QuestionResponse> questionResponses = cachedQuestionResponses.stream()
                    .map(cachedQuestionResponse -> QuestionResponse.builder()
                            .questionId(cachedQuestionResponse.questionId())
                            .title(cachedQuestionResponse.title())
                            .content(cachedQuestionResponse.content())
                            .userId(cachedQuestionResponse.userId())
                            .likeCount(cachedQuestionResponse.likeCount())
                            .reviewCount(cachedQuestionResponse.reviewCount())
                            .createdAt(cachedQuestionResponse.createdAt())
                            .updatedAt(cachedQuestionResponse.updatedAt())
                            .build()
                    ).toList();
            return new PageImpl<>(questionResponses, pageable, cachedQuestionPage.getTotalElements());
        }

        Page<QuestionRdsResponse> result = questionRdsQueryService.findQuestionsByKeyword(keyword, pageable);
        List<CachedQuestionResponse> toCache = result.getContent().stream()
                .map(questionRdsResponse -> CachedQuestionResponse.builder()
                        .questionId(questionRdsResponse.questionId())
                        .title(questionRdsResponse.title())
                        .content(questionRdsResponse.content())
                        .userId(questionRdsResponse.userId())
                        .likeCount(questionRdsResponse.likeCount())
                        .reviewCount(questionRdsResponse.reviewCount())
                        .createdAt(questionRdsResponse.createdAt())
                        .updatedAt(questionRdsResponse.updatedAt())
                        .build()
                ).toList();
        CachedQuestionPage cachedQuestionPage = CachedQuestionPage.builder()
                .content(toCache)
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .currentPage(result.getNumber())
                .build();
        questionRedisQueryService.setCachedQuestionResponse(keyword, page, cachedQuestionPage);

        return result.map(questionRdsResponse -> QuestionResponse.builder()
                .questionId(questionRdsResponse.questionId())
                .title(questionRdsResponse.title())
                .content(questionRdsResponse.content())
                .userId(questionRdsResponse.userId())
                .likeCount(questionRdsResponse.likeCount())
                .reviewCount(questionRdsResponse.reviewCount())
                .createdAt(questionRdsResponse.createdAt())
                .updatedAt(questionRdsResponse.updatedAt())
                .build()
        );
    }
}
