package com.recycle.api.question.usecase;

import com.recycle.api.question.service.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionQueryUsecase {
    private final QuestionQueryService questionQueryService;

    @Transactional(readOnly = true)
    public void getQuestionById(Long questionId) {
        questionQueryService.getQuestionById(questionId);
    }

    @Transactional(readOnly = true)
    public void findQuestionsByTop10Reviewed() {
        questionQueryService.findQuestionsByTop10Reviewed();
    }

    @Transactional(readOnly = true)
    public void getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        questionQueryService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, page, size);
    }
}
