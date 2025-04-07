package com.recycle.api.question.usecase;

import com.recycle.api.question.dto.response.QuestionByUserResponse;
import com.recycle.api.question.dto.response.QuestionResponse;
import com.recycle.api.question.service.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionQueryUsecase {
    private final QuestionQueryService questionQueryService;

    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long questionId) {
        return questionQueryService.getQuestionById(questionId);
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> findQuestionsByTop10Reviewed() {
        return questionQueryService.findQuestionsByTop10Reviewed();
    }

    @Transactional(readOnly = true)
    public Page<QuestionByUserResponse> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        return questionQueryService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, page, size);
    }

    @Transactional(readOnly = true)
    public Page<QuestionResponse> findUserInterestedQuestions(Long userId, int page, int size) {
        return questionQueryService.findUserInterestedQuestions(userId, page, size);
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestionsByUserId(Long userId) {
        return questionQueryService.getQuestionsByUserId(userId);
    }

    public Page<QuestionResponse> findQuestionsByKeyword(String keyword, int page, int size) {
//        return questionQueryService.findQuestionsByKeyword(keyword, page, size);
        return questionQueryService.searchQuestions(keyword, page, size);
    }
}
