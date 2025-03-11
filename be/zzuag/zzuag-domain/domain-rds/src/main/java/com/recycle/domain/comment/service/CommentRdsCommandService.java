package com.recycle.domain.comment.service;

import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentRdsCommandService {
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Comment comment) {
        comment.delete();
    }
}
