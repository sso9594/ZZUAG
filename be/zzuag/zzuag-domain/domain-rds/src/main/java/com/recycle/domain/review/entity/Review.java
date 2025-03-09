package com.recycle.domain.review.entity;

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
public class Review extends AbstractPostEntity {
    @Embedded
    private ReviewMetaData metaData;

    public static Review create(Long userId, Question question, ReviewPoint startPoint, ReviewPoint endPoint,
                                ReviewType tag, String content) {
        return Review.builder()
                .metaData(ReviewMetaData.create(
                        question,
                        startPoint,
                        endPoint,
                        tag)
                )
                .content(content)
                .userId(userId)
                .likeCount(0)
                .build();
    }

    public void update(Question question, ReviewPoint startPoint, ReviewPoint endPoint, ReviewType tag, String content) {
        this.metaData = ReviewMetaData.builder()
                .question(question)
                .startPoint(startPoint)
                .endPoint(endPoint)
                .tag(tag)
                .build();
        this.content = content;
    }

    public boolean isValid(Long userId) {
        return super.getUserId().equals(userId);
    }
}
