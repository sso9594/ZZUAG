package com.zzuag.post_command_service.domain.question.service.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class QuestionUnlikeEvent {
    private Long questionId;
    private Long userId;

    public static QuestionUnlikeEvent create(Long questionId, Long userId) {
        return QuestionUnlikeEvent.builder()
                .questionId(questionId)
                .userId(userId)
                .build();
    }
}
