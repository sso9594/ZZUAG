package com.recycle.service.question.service;

import com.recycle.domain.question.entity.QuestionFavorite;
import com.recycle.domain.question.service.QuestionFavoriteRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionFavoriteCommandDomainService {
    private final QuestionFavoriteRdsCommandService questionFavoriteRdsCommandService;

    public void createQuestionFavorite(QuestionFavorite questionFavorite) {
        questionFavoriteRdsCommandService.createQuestionFavorite(questionFavorite);
    }

    public void deleteQuestionFavorite(Long questionId, Long userId) {
        questionFavoriteRdsCommandService.deleteQuestionFavorite(questionId, userId);
    }
}
