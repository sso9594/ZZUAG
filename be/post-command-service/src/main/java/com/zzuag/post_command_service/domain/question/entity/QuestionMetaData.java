package com.zzuag.post_command_service.domain.question.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Embeddable
public class QuestionMetaData {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "review_count", nullable = false)
    private int reviewCount;
    public static QuestionMetaData create(String title) {
        return QuestionMetaData.builder()
                .title(title)
                .reviewCount(0)
                .build();
    }
}
