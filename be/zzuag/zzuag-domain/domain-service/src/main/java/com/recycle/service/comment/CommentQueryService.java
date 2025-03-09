package com.recycle.service.comment;

import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.service.CommentRdsQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentQueryService {
    private final CommentRdsQueryService commentRdsQueryService;

    public Optional<Comment> getCommentById(Long commentId){
        return commentRdsQueryService.getCommentById(commentId);
    }
}