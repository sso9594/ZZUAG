package com.recycle.domain.question.exception.exceptions;

import com.recycle.domain.question.exception.QuestionSearchErrCode;

public class InternalServerException extends RuntimeException{
    private final QuestionSearchErrCode questionSearchErrCode;

    public InternalServerException(QuestionSearchErrCode questionSearchErrCode) {
        super(questionSearchErrCode.getMessage());
        this.questionSearchErrCode = questionSearchErrCode;
    }

    public QuestionSearchErrCode getQuestionSearchErrCode() {
        return questionSearchErrCode;
    }

    public String getErrMessage() {
        return questionSearchErrCode.getMessage();
    }
}
