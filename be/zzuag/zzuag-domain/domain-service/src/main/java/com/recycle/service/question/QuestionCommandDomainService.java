package com.recycle.service.question;

import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.service.QuestionRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionCommandDomainService {
    private final QuestionRdsCommandService questionRdsCommandService;

    @Transactional
    public void createQuestion(Question question){
        questionRdsCommandService.createQuestion(question);
    }

    @Transactional
    public void updateQuestion(Question question){
        questionRdsCommandService.updateQuestion(question);
    }

    @Transactional
    public void deleteQuestion(Question question){
        questionRdsCommandService.deleteQuestion(question);
    }
}
