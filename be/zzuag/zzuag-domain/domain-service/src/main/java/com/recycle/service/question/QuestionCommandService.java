package com.recycle.service.question;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.service.QuestionRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionCommandService {
    private final QuestionRdsCommandService questionRdsCommandService;

    public void createQuestion(Question question){
        questionRdsCommandService.createQuestion(question);
    }

    public void updateQuestion(Question question){
        questionRdsCommandService.updateQuestion(question);
    }

    public void deleteQuestion(Question question){
        questionRdsCommandService.deleteQuestion(question);
    }
}
