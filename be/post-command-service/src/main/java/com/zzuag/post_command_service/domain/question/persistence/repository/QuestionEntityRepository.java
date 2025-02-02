package com.zzuag.post_command_service.domain.question.persistence.repository;

import com.zzuag.post_command_service.domain.question.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionEntityRepository extends JpaRepository<QuestionEntity, Long>{
    Optional<QuestionEntity> findByIdAndIsDeletedFalse(Long id);
}
