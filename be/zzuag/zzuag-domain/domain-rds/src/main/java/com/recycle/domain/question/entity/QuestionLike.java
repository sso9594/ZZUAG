package com.recycle.domain.question.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class QuestionLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_like_id")
    private Long questionLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 해당 메서드를 발급하고 내부 이벤트를 생성해 QuestionEntity에서 likeCount를 증가
    public static QuestionLike create(Question question, Long userId) {
        return QuestionLike.builder()
                .question(question)
                .userId(userId)
                .build();
    }
}
