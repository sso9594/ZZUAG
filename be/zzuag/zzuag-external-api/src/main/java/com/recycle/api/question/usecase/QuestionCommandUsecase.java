package com.recycle.api.question.usecase;

import com.recycle.api.question.dto.request.QuestionCreateRequest;
import com.recycle.api.question.dto.request.QuestionUpdateRequest;
import com.recycle.api.question.service.QuestionCommandService;
import com.recycle.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionCommandUsecase {
    private final QuestionCommandService questionCommandService;

    public void createQuestion(Long userId, QuestionCreateRequest request) {
        questionCommandService.createQuestion(
                Question.create(
                        userId,
                        request.title(),
                        request.content()
                ));
    }

    public void updateQuestion(Long userId, Long questionId, QuestionUpdateRequest request) {
        questionCommandService.updateQuestion(userId, questionId, request);
    }

    public void deleteQuestion(Long userId, Long questionId) {
        questionCommandService.deleteQuestion(userId, questionId);
    }
}
