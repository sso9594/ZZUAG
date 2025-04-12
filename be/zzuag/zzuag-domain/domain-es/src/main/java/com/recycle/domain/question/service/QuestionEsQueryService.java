package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.QuestionIdResult;
import com.recycle.domain.question.exception.QuestionSearchErrCode;
import com.recycle.domain.question.exception.exceptions.InternalServerException;
import com.recycle.domain.question.repository.QuestionEsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QuestionEsQueryService {
    private final QuestionEsRepository questionEsRepository;

    public QuestionIdResult findQuestionsByKeyword(String keyword, Pageable pageable){
        try {
            return questionEsRepository.searchQuestionIdsByKeyword(keyword, pageable);
        } catch (IOException e){
            throw new InternalServerException(QuestionSearchErrCode.INTERNAL_SERVER_ERROR);
        }
    }
}
