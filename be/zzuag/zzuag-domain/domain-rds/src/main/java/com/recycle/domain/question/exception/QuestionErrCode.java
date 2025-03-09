package com.recycle.domain.question.exception;

import com.recycle.exception.HttpStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionErrCode {
    NO_SUCH_QUESTION(HttpStatus.BAD_REQUEST, "해당 질문이 존재하지 않습니다."),
    INVALID_USER(HttpStatus.FORBIDDEN, "해당 사용자는 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
