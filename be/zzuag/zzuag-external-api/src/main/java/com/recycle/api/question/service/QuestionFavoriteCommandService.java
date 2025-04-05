package com.recycle.api.question.service;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.entity.QuestionFavorite;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.service.question.service.QuestionFavoriteCommandDomainService;
import com.recycle.service.question.service.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionFavoriteCommandService {
    private final QuestionFavoriteCommandDomainService questionFavoriteCommandDomainService;
    private final QuestionQueryDomainService questionQueryDomainService;

    public void createQuestionFavorite(Long questionId, Long userId) {
        Question target = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        questionFavoriteCommandDomainService.createQuestionFavorite(
                QuestionFavorite.create(questionId, target)
        );
    }

    public void deleteQuestionFavorite(Long questionId, Long userId) {
        questionFavoriteCommandDomainService.deleteQuestionFavorite(questionId, userId);
    }
}
