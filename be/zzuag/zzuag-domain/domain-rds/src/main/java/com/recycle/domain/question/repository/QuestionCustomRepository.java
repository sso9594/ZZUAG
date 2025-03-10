package com.recycle.domain.question.repository;

import com.recycle.domain.question.dto.QuestionWithReviewLikesByUserDTO;
import com.recycle.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionCustomRepository {
    List<Question> findQuestionsByTop10Reviewed();
    Page<QuestionWithReviewLikesByUserDTO> getQuestionsByUserIdAndTopLikeCountByPagination(Long userId, Pageable pageable);
}
