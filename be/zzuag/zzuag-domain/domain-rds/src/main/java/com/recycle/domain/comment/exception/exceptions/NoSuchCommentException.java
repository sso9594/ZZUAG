package com.recycle.domain.comment.exception.exceptions;

import com.recycle.domain.comment.exception.CommentErrCode;

public class NoSuchCommentException extends RuntimeException{
    private final CommentErrCode commentErrCode;
    public NoSuchCommentException(CommentErrCode commentErrCode) {
        super(commentErrCode.getMessage());
        this.commentErrCode = commentErrCode;
    }

    public CommentErrCode getCommentErrCode() {
        return commentErrCode;
    }

    public String getErrMessage() {
        return commentErrCode.getMessage();
    }
}
