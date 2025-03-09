package com.recycle.api.review.service;

import com.recycle.api.review.dto.ReviewCreateRequest;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.exception.ReviewErrCode;
import com.recycle.domain.review.exception.exceptions.InvalidReviewUserException;
import com.recycle.domain.review.exception.exceptions.NoSuchReviewException;
import com.recycle.service.question.QuestionQueryService;
import com.recycle.service.review.ReviewCommandService;
import com.recycle.service.review.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewCommandService reviewCommandService;
    private final QuestionQueryService questionQueryService;
    private final ReviewQueryService reviewQueryService;

    @Transactional
    public void createReview(Long userId, Long questionId, ReviewCreateRequest request) {
        Question targetQuestion = questionQueryService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        reviewCommandService.createReview(
                Review.create(
                        userId,
                        targetQuestion,
                        request.startPoint(),
                        request.endPoint(),
                        request.tag(),
                        request.content()
                )
        );
    }

    @Transactional
    public void updateReview(Long userId, Long reviewId, ReviewCreateRequest request) {
        Review targetReview = reviewQueryService.getReviewById(reviewId)
                .orElseThrow(() -> new NoSuchReviewException(ReviewErrCode.NO_SUCH_REVIEW));
        if(!targetReview.isValid(userId)) {
            throw new InvalidReviewUserException(ReviewErrCode.INVALID_USER);
        }
        targetReview.update(
                targetReview.getMetaData().getQuestion(),
                request.startPoint(),
                request.endPoint(),
                request.tag(),
                request.content()
        );
        reviewCommandService.updateReview(targetReview);
    }

    @Transactional
    public void deleteReview(Long userId, Long reviewId) {
        Review targetReview = reviewQueryService.getReviewById(reviewId)
                .orElseThrow(() -> new NoSuchReviewException(ReviewErrCode.NO_SUCH_REVIEW));
        if(!targetReview.isValid(userId)) {
            throw new InvalidReviewUserException(ReviewErrCode.INVALID_USER);
        }
        reviewCommandService.deleteReview(targetReview);
    }
}
