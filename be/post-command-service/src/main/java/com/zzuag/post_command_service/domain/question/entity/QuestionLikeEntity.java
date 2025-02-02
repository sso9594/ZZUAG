package com.zzuag.post_command_service.domain.question.entity;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionLikeCreateRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class QuestionLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_like_id")
    private Long questionLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity questionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 해당 메서드를 발급하고 내부 이벤트를 생성해 QuestionEntity에서 likeCount를 증가
    public static QuestionLikeEntity create(QuestionEntity question, QuestionLikeCreateRequest request) {
        return QuestionLikeEntity.builder()
                .questionId(question)
                .userId(request.userId())
                .build();
    }
}
