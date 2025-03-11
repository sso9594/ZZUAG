package com.recycle.api.question.service;

import com.recycle.api.question.dto.response.QuestionByUserResponse;
import com.recycle.api.question.dto.response.QuestionResponse;
import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import com.recycle.service.question.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionQueryDomainService questionQueryDomainService;

    @Transactional(readOnly = true)
    public Question getQuestionById(Long questionId) {
        return questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> findQuestionsByTop10Reviewed() {
        return questionQueryDomainService.findQuestionsByTop10Reviewed()
                .stream()
                .map(QuestionResponse::convert)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<QuestionByUserResponse> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<QuestionByUserResponse> result = questionQueryDomainService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable)
                .stream()
                .map(QuestionByUserResponse::convert)
                .toList();

        return new PageImpl<>(result, pageable, result.size());
    }

}
