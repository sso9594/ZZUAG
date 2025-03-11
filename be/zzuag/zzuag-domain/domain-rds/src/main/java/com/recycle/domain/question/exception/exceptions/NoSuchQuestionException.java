package com.recycle.domain.question.exception.exceptions;

import com.recycle.domain.question.exception.QuestionErrCode;

public class NoSuchQuestionException extends RuntimeException{
    private final QuestionErrCode questionErrCode;
    public NoSuchQuestionException(QuestionErrCode questionErrCode) {
        super(questionErrCode.getMessage());
        this.questionErrCode = questionErrCode;
    }

    public QuestionErrCode getQuestionErrCode() {
        return questionErrCode;
    }

    public String getErrMessage() {
        return questionErrCode.getMessage();
    }
}
