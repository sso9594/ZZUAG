package com.recycle.api.question.service;

import com.recycle.api.question.dto.request.QuestionUpdateRequest;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.exception.exceptions.InvalidQuestionUserException;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.service.question.QuestionCommandDomainService;
import com.recycle.service.question.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionCommandService {
    private final QuestionCommandDomainService questionCommandDomainService;
    private final QuestionQueryDomainService questionQueryDomainService;

    @Transactional
    public void createQuestion(Question question) {
        questionCommandDomainService.createQuestion(question);
    }

    @Transactional
    public void updateQuestion(Long userId, Long questionId, QuestionUpdateRequest request) {
        Question question = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        if(!question.isValid(userId)) {
            throw new InvalidQuestionUserException(QuestionErrCode.INVALID_USER);
        }
        question.update(request.title(), request.content());
        questionCommandDomainService.updateQuestion(question);
    }

    @Transactional
    public void deleteQuestion(Long userId, Long questionId) {
        Question question = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        if(!question.isValid(userId)) {
            throw new InvalidQuestionUserException(QuestionErrCode.INVALID_USER);
        }
        questionCommandDomainService.deleteQuestion(question);
    }
}
