package com.recycle.service.question;

import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.service.QuestionRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionQueryService {
    private final QuestionRdsQueryService questionRdsQueryService;

    @Transactional(readOnly = true)
    public Optional<Question> getQuestionById(Long questionId) {
        return questionRdsQueryService.getQuestionById(questionId);
    }

    @Transactional(readOnly = true)
    public List<Question> findQuestionsByTop10Reviewed() {
        return questionRdsQueryService.findQuestionsByTop10Reviewed();
    }

    @Transactional(readOnly = true)
    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return questionRdsQueryService.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }
}
