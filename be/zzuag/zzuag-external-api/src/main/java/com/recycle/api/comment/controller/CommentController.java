package com.recycle.api.comment.controller;

import com.recycle.api.comment.api.CommentApi;
import com.recycle.api.comment.dto.CommentCreateRequest;
import com.recycle.api.comment.dto.CommentUpdateRequest;
import com.recycle.api.comment.usecase.CommentUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class CommentController implements CommentApi {
    private final CommentUsecase commentUsecase;

    @PostMapping("/{questionId}/comment")
    public ResponseEntity<Void> createParentComment(@PathVariable Long questionId, @RequestBody @Valid CommentCreateRequest request) {
        Long userId = 1L;
        commentUsecase.createComment(userId, questionId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{questionId}/comment/{commentId}")
    public ResponseEntity<Void> createChildComment(@PathVariable Long questionId, @PathVariable Long commentId, @RequestBody @Valid CommentCreateRequest request) {
        Long userId = 1L;
        commentUsecase.createComment(userId, questionId, commentId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{questionId}/comment/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentUpdateRequest request) {
        Long userId = 1L;
        commentUsecase.updateComment(userId, commentId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{questionId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        Long userId = 1L;
        commentUsecase.deleteComment(userId, commentId);
        return ResponseEntity.ok().build();
    }
}
