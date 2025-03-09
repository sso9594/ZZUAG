package com.recycle.domain.review.exception.exceptions;

import com.recycle.domain.review.exception.ReviewErrCode;

public class NoSuchReviewException extends RuntimeException{
    private final ReviewErrCode reviewErrCode;

    public NoSuchReviewException(ReviewErrCode reviewErrCode) {
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
