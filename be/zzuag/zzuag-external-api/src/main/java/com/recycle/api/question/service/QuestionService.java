package com.recycle.api.question.service;

import com.recycle.api.question.dto.QuestionUpdateRequest;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.exception.exceptions.InvalidQuestionUserException;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.service.question.QuestionCommandService;
import com.recycle.service.question.QuestionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    @Transactional
    public void createQuestion(Question question) {
        questionCommandService.createQuestion(question);
    }

    @Transactional
    public void updateQuestion(Long userId, Long questionId, QuestionUpdateRequest request) {
        Question question = questionQueryService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        if(!question.isValid(userId)) {
            throw new InvalidQuestionUserException(QuestionErrCode.INVALID_USER);
        }
        question.update(request.title(), request.content());
        questionCommandService.updateQuestion(question);
    }

    @Transactional
    public void deleteQuestion(Long userId, Long questionId) {
        Question question = questionQueryService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        if(!question.isValid(userId)) {
            throw new InvalidQuestionUserException(QuestionErrCode.INVALID_USER);
        }
        questionCommandService.deleteQuestion(question);
    }
}
