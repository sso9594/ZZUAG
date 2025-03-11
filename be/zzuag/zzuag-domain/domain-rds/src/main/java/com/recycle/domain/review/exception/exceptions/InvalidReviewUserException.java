package com.recycle.domain.review.exception.exceptions;

import com.recycle.domain.review.exception.ReviewErrCode;

public class InvalidReviewUserException extends RuntimeException{
    private final ReviewErrCode reviewErrCode;

    public InvalidReviewUserException(ReviewErrCode reviewErrCode) {
        super(reviewErrCode.getMessage());
        this.reviewErrCode = reviewErrCode;
    }

    public ReviewErrCode getReviewErrCode() {
        return reviewErrCode;
    }

    public String getErrMessage() {
        return reviewErrCode.getMessage();
    }
}
