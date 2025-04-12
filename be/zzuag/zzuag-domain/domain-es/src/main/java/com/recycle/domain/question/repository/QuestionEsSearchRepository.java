package com.recycle.domain.question.repository;

import com.recycle.domain.question.dto.QuestionIdResult;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface QuestionEsSearchRepository {
    public QuestionIdResult searchQuestionIdsByKeyword(String keyword, Pageable pageable) throws IOException;
}
