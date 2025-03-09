package com.recycle.domain.question.service;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionRdsCommandService {

    private final QuestionRepository questionRepository;

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(Question question){
        question.delete();
    }

    // 좋아요 증가 로직은 업데이트 쿼리로 처리

}