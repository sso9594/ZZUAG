package com.recycle.domain.question.repository;

import com.recycle.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRdsRepository extends JpaRepository<Question, Long>, QuestionCustomRepository {
    Optional<Question> findByIdAndIsDeletedFalse(Long id);
    List<Question> findByUserIdAndIsDeletedFalse(Long userId);

    List<Question> findByIdInAndIsDeletedFalse(List<Long> questionIds);
}
