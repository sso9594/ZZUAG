package com.recycle.api.comment.usecase;

import com.recycle.api.comment.dto.CommentCreateRequest;
import com.recycle.api.comment.dto.CommentUpdateRequest;
import com.recycle.api.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentUsecase {
    private final CommentService commentService;

    public void createComment(Long userId, Long questionId, CommentCreateRequest request) {
        commentService.createComment(userId, questionId, request);
    }

    public void createComment(Long userId, Long questionId, Long commentId, CommentCreateRequest request) {
        commentService.createComment(userId, questionId, commentId, request);
    }

    public void updateComment(Long userId, Long commentId, CommentUpdateRequest request) {
        commentService.updateComment(userId, commentId, request);
    }

    public void deleteComment(Long userId, Long commentId) {
        commentService.deleteComment(userId, commentId);
    }
}
