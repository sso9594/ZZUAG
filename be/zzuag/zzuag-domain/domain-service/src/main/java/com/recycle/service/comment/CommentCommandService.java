package com.recycle.service.comment;

import com.recycle.domain.comment.entity.Comment;
import com.recycle.domain.comment.service.CommentRdsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentCommandService {
    private final CommentRdsCommandService commentRdsCommandService;

    public void createComment(Comment comment){
        commentRdsCommandService.createComment(comment);
    }

    public void updateComment(Comment comment){
        commentRdsCommandService.updateComment(comment);
    }

    public void deleteComment(Comment comment){
        commentRdsCommandService.deleteComment(comment);
    }
}
