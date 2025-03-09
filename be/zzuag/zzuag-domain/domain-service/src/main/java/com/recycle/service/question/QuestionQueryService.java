package com.recycle.service.question;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.service.QuestionRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionQueryService {
    private final QuestionRdsQueryService questionRdsQueryService;

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRdsQueryService.getQuestionById(questionId);
    }
}
