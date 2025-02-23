package com.zzuag.post_command_service.domain.question.service.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionLikeEvent {
    private Long questionId;
    private Long userId;

   public static QuestionLikeEvent create(Long questionId, Long userId) {
        return QuestionLikeEvent.builder()
                .questionId(questionId)
                .userId(userId)
                .build();
    }
}
