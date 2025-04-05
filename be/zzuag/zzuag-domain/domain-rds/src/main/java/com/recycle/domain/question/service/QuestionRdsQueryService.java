package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.QuestionRdsResponse;
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

    public Page<Question> findUserInterestedQuestions(Long userId, Pageable pageable) {
        return questionRepository.findUserInterestedQuestions(userId, pageable);
    }

    public List<Question> getQuestionsByUserId(Long userId) {
        return questionRepository.findByUserIdAndIsDeletedFalse(userId);
    }

    public Page<QuestionRdsResponse> findQuestionsByKeyword(String keyword, Pageable pageable) {
        Page<Question> result = questionRepository.findQuestionsByKeyword(keyword, pageable);
        return result.map(question -> QuestionRdsResponse.builder()
                .questionId(question.getId())
                .title(question.getMetaData().getTitle())
                .content(question.getContent())
                .userId(question.getUserId())
                .likeCount(question.getLikeCount())
                .reviewCount(question.getMetaData().getReviewCount())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build(
        ));
    }
}
