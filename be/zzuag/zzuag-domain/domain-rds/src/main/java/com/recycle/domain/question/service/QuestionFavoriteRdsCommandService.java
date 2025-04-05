package com.recycle.domain.question.service;

import com.recycle.domain.question.entity.QuestionFavorite;
import com.recycle.domain.question.repository.QuestionFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionFavoriteRdsCommandService {
    private final QuestionFavoriteRepository questionFavoriteRepository;

    public void createQuestionFavorite(QuestionFavorite questionFavorite) {
        questionFavoriteRepository.save(questionFavorite);
    }

    public void deleteQuestionFavorite(Long questionId, Long userId) {
        questionFavoriteRepository.deleteByQuestionIdAndUserId(questionId, userId);
    }
}
