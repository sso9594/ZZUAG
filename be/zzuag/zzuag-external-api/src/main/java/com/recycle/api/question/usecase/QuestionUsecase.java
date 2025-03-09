package com.recycle.api.question.usecase;

import com.recycle.api.question.dto.QuestionCreateRequest;
import com.recycle.api.question.dto.QuestionUpdateRequest;
import com.recycle.api.question.service.QuestionService;
import com.recycle.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionUsecase {
    private final QuestionService questionService;

    public void createQuestion(Long userId, QuestionCreateRequest request) {
        questionService.createQuestion(
                Question.create(
                        userId,
                        request.title(),
                        request.content()
                ));
    }

    public void updateQuestion(Long userId, Long questionId, QuestionUpdateRequest request) {
        questionService.updateQuestion(userId, questionId, request);
    }

    public void deleteQuestion(Long userId, Long questionId) {
        questionService.deleteQuestion(userId, questionId);
    }
}
