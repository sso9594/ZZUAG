package com.recycle.domain.comment.service;

import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentRdsQueryService {
    private final CommentRepository commentRepository;

    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findByIdAndIsDeletedFalse(commentId);
    }
}
