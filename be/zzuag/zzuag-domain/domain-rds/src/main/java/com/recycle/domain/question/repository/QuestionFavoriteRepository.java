package com.recycle.domain.question.repository;

import com.recycle.domain.question.entity.QuestionFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionFavoriteRepository extends JpaRepository<QuestionFavorite, Long> {
    void deleteByQuestionIdAndUserId(Long questionId, Long userId);
}
