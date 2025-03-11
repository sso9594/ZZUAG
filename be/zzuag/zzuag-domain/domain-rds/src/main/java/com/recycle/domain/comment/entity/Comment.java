package com.recycle.domain.comment.entity;

import com.recycle.domain.AbstractPostEntity;
import com.recycle.domain.question.entity.Question;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@DynamicUpdate
@Entity
public class Comment extends AbstractPostEntity {
    @Embedded
    private CommentMetaData metaData;

    public static Comment create (Question question, Long userId, String content) {
        return Comment.builder()
                .metaData(CommentMetaData.create(
                        question
                ))
                .content(content)
                .userId(userId)
                .likeCount(0)
                .build();
    }

    public static Comment create (Question question, Comment comment, Long userId, String content) {
        return Comment.builder()
                .metaData(CommentMetaData.create(
                        question,
                        comment
                ))
                .content(content)
                .userId(userId)
                .likeCount(0)
                .build();
    }

    public void update(String content) {
        this.content = content;
    }

    public boolean isValid(Long userId) {
        return super.getUserId().equals(userId);
    }
}
