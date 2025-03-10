package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionRdsQueryService {
    private final QuestionRepository questionRepository;

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findByIdAndIsDeletedFalse(questionId);
    }

    public List<Question> findQuestionsByTop10Reviewed() {
        return questionRepository.findQuestionsByTop10Reviewed();
    }

    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return questionRepository.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }


}
