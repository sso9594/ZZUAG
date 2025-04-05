package com.recycle.api.review.service;

import com.recycle.api.review.dto.request.ReviewCreateRequest;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.domain.review.entity.Review;
import com.recycle.domain.review.exception.ReviewErrCode;
import com.recycle.domain.review.exception.exceptions.InvalidReviewUserException;
import com.recycle.domain.review.exception.exceptions.NoSuchReviewException;
import com.recycle.service.question.service.QuestionQueryDomainService;
import com.recycle.service.review.service.ReviewCommandDomainService;
import com.recycle.service.review.service.ReviewQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ReviewCommandDomainService reviewCommandDomainService;
    private final QuestionQueryDomainService questionQueryDomainService;
    private final ReviewQueryDomainService reviewQueryDomainService;

    @Transactional
    public void createReview(Long userId, Long questionId, ReviewCreateRequest request) {
        Question targetQuestion = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(() -> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        reviewCommandDomainService.createReview(
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
        Review targetReview = reviewQueryDomainService.getReviewById(reviewId)
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
        reviewCommandDomainService.updateReview(targetReview);
    }

    @Transactional
    public void deleteReview(Long userId, Long reviewId) {
        Review targetReview = reviewQueryDomainService.getReviewById(reviewId)
                .orElseThrow(() -> new NoSuchReviewException(ReviewErrCode.NO_SUCH_REVIEW));
        if(!targetReview.isValid(userId)) {
            throw new InvalidReviewUserException(ReviewErrCode.INVALID_USER);
        }
        reviewCommandDomainService.deleteReview(targetReview);
    }
}
