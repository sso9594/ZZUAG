package com.recycle.api.comment.service;

import com.recycle.api.comment.dto.CommentCreateRequest;
import com.recycle.api.comment.dto.CommentUpdateRequest;
import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.exception.CommentErrCode;
import com.recycle.domain.comment.exception.exceptions.InvalidCommentUserException;
import com.recycle.domain.comment.exception.exceptions.NoSuchCommentException;
import com.recycle.domain.question.entity.Question;
import com.recycle.domain.question.exception.QuestionErrCode;
import com.recycle.domain.question.exception.exceptions.NoSuchQuestionException;
import com.recycle.service.comment.CommentCommandService;
import com.recycle.service.comment.CommentQueryService;
import com.recycle.service.question.QuestionQueryDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;
    private final QuestionQueryDomainService questionQueryDomainService;

    @Transactional
    public void createComment(Long userId, Long questionId, CommentCreateRequest request) {
        Question targetQuestion = questionQueryDomainService.getQuestionById(questionId)
                        .orElseThrow(()-> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        Comment comment = Comment.create(targetQuestion, userId, request.content());
        commentCommandService.createComment(comment);
    }

    @Transactional
    public void createComment(Long userId, Long questionId, Long commentId, CommentCreateRequest request) {
        Question targetQuestion = questionQueryDomainService.getQuestionById(questionId)
                .orElseThrow(()-> new NoSuchQuestionException(QuestionErrCode.NO_SUCH_QUESTION));
        Comment targetComment = commentQueryService.getCommentById(commentId)
                .orElseThrow(() -> new NoSuchCommentException(CommentErrCode.NO_SUCH_COMMENT));
        Comment comment = Comment.create(targetQuestion, targetComment, userId, request.content());
        commentCommandService.createComment(comment);
    }

    @Transactional
    public void updateComment(Long userId, Long commentId, CommentUpdateRequest request) {
        Comment comment = commentQueryService.getCommentById(commentId)
                .orElseThrow(() -> new NoSuchCommentException(CommentErrCode.NO_SUCH_COMMENT));
        if(!comment.isValid(userId)) {
            throw new InvalidCommentUserException(CommentErrCode.INVALID_USER);
        }
        comment.update(request.content());
        commentCommandService.updateComment(comment);
    }

    @Transactional
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentQueryService.getCommentById(commentId)
                .orElseThrow(() -> new NoSuchCommentException(CommentErrCode.NO_SUCH_COMMENT));
        if(!comment.isValid(userId)) {
            throw new InvalidCommentUserException(CommentErrCode.INVALID_USER);
        }
        commentCommandService.deleteComment(comment);
    }
}
