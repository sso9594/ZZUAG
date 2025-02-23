package com.zzuag.post_command_service.domain.question.persistence.repository;

import com.zzuag.post_command_service.domain.question.entity.QuestionLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionLikeEntityRepository extends JpaRepository<QuestionLikeEntity, Long> {
    void deleteByQuestionIdAndUserId(Long questionId, Long userId);
    Optional<QuestionLikeEntity> findByQuestionIdAndUserId(Long questionId, Long userId);
}
