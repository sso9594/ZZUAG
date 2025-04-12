package com.recycle.domain.question.exception;

import com.recycle.exception.HttpStatus;

public enum QuestionSearchErrCode {
    NO_SUCH_QUESTION(HttpStatus.BAD_REQUEST, "해당 질문이 존재하지 않습니다."),
    INVALID_KEYWORD(HttpStatus.BAD_REQUEST, "잘못된 검색어입니다."),
    INVALID_PAGEABLE(HttpStatus.BAD_REQUEST, "잘못된 페이지 정보입니다."),
    INVALID_SORT(HttpStatus.BAD_REQUEST, "잘못된 정렬 정보입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    QuestionSearchErrCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
