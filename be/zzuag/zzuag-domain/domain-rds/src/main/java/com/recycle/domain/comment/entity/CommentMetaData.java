package com.recycle.domain.comment.entity;

import com.recycle.domain.question.entity.Question;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Embeddable
public class CommentMetaData {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment comment;

    public static CommentMetaData create(Question question) {
        return CommentMetaData.builder()
                .question(question)
                .comment(null)
                .build();
    }

    public static CommentMetaData create(Question question, Comment comment) {
        return CommentMetaData.builder()
                .question(question)
                .comment(comment)
                .build();
    }
}
