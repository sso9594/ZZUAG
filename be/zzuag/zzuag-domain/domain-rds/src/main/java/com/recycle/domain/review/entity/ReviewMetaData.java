package com.recycle.domain.review.entity;

import com.recycle.domain.question.entity.Question;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Embeddable
public class ReviewMetaData {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "point", column = @Column(name = "start_point_point")),
            @AttributeOverride(name = "index", column = @Column(name = "start_point_index"))
    })
    private ReviewPoint startPoint;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "point", column = @Column(name = "end_point_point")),
            @AttributeOverride(name = "index", column = @Column(name = "end_point_index"))
    })
    private ReviewPoint endPoint;

    private ReviewType tag;

    public static ReviewMetaData create(Question question, ReviewPoint startPoint, ReviewPoint endPoint, ReviewType tag) {
        return ReviewMetaData.builder()
                .question(question)
                .startPoint(startPoint)
                .endPoint(endPoint)
                .tag(tag)
                .build();
    }
}
