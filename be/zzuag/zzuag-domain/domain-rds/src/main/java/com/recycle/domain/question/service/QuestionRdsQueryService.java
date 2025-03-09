package com.recycle.domain.question.service;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionRdsQueryService {
    private final QuestionRepository questionRepository;

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findByIdAndIsDeletedFalse(questionId);
    }
}
