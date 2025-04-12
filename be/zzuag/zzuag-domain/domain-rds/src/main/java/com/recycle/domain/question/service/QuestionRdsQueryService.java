package com.recycle.domain.question.service;

import com.recycle.domain.question.dto.QuestionRdsResponse;
import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.repository.QuestionRdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionRdsQueryService {
    private final QuestionRdsRepository questionRdsRepository;

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRdsRepository.findByIdAndIsDeletedFalse(questionId);
    }

    public List<Question> findQuestionsByTop10Reviewed() {
        return questionRdsRepository.findQuestionsByTop10Reviewed();
    }

    public Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable) {
        return questionRdsRepository.getQuestionsByUserIdAndTopLikeCountByPagination(userId, pageable);
    }

    public Page<Question> findUserInterestedQuestions(Long userId, Pageable pageable) {
        return questionRdsRepository.findUserInterestedQuestions(userId, pageable);
    }

    public List<Question> getQuestionsByUserId(Long userId) {
        return questionRdsRepository.findByUserIdAndIsDeletedFalse(userId);
    }

    public List<QuestionRdsResponse> findQuestionsByIds(List<Long> questionIds) {
        List<Question> questions = questionRdsRepository.findByIdInAndIsDeletedFalse(questionIds);
        return questions.stream()
                .map(question -> QuestionRdsResponse.builder()
                        .questionId(question.getId())
                        .title(question.getMetaData().getTitle())
                        .content(question.getContent())
                        .userId(question.getUserId())
                        .likeCount(question.getLikeCount())
                        .reviewCount(question.getMetaData().getReviewCount())
                        .createdAt(question.getCreatedAt())
                        .updatedAt(question.getUpdatedAt())
                        .build())
                .toList();
    }

    public Page<QuestionRdsResponse> findQuestionsByKeyword(String keyword, Pageable pageable) {
        Page<Question> result = questionRdsRepository.findQuestionsByKeyword(keyword, pageable);
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
