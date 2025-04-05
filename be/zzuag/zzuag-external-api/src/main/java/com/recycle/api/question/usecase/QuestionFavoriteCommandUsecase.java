package com.recycle.api.question.usecase;

import com.recycle.api.question.service.QuestionFavoriteCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class QuestionFavoriteCommandUsecase {
    private final QuestionFavoriteCommandService questionFavoriteCommandService;

    public void createQuestionFavorite(Long userId, Long questionId) {
        questionFavoriteCommandService.createQuestionFavorite(userId, questionId);
    }

    public void deleteQuestionFavorite(Long userId, Long questionId) {
        questionFavoriteCommandService.deleteQuestionFavorite(userId, questionId);
    }
}
