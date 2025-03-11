package com.recycle.service.comment;

import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.service.CommentRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentCommandService {
    private final CommentRdsCommandService commentRdsCommandService;

    @Transactional
    public void createComment(Comment comment){
        commentRdsCommandService.createComment(comment);
    }

    @Transactional
    public void updateComment(Comment comment){
        commentRdsCommandService.updateComment(comment);
    }

    @Transactional
    public void deleteComment(Comment comment){
        commentRdsCommandService.deleteComment(comment);
    }
}
