package com.recycle.domain.comment.exception;

public enum CommentErrCode {
    NO_SUCH_COMMENT("해당 댓글이 존재하지 않습니다."),
    INVALID_USER("해당 사용자는 권한이 없습니다.");

    private final String message;

    CommentErrCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
