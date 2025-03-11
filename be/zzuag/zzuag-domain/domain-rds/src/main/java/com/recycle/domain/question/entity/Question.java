package com.recycle.domain.question.entity;

import com.recycle.domain.AbstractPostEntity;
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
public class Question extends AbstractPostEntity {

    @Embedded
    private QuestionMetaData metaData;

    public static Question create (Long userId, String title, String content) {
        return Question.builder()
                .metaData(QuestionMetaData.create(title))
                .content(content)
                .userId(userId)
                .likeCount(0)
                .build();
    }

    public void update(String title, String content) {
        this.metaData = QuestionMetaData.create(title);
        this.content = content;
    }

    public boolean isValid(Long userId) {
        return super.getUserId().equals(userId);
    }

}
