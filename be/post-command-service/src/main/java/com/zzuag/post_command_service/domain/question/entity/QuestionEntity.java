package com.zzuag.post_command_service.domain.question.entity;

import com.zzuag.common_module.passport.Passport;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionCreateRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionEditRequest;
import com.zzuag.post_command_service.global.entity.AbstractPostEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@DynamicUpdate
@Entity
public class QuestionEntity extends AbstractPostEntity {

    @Embedded
    private QuestionMetaData metaData;

    public static QuestionEntity create (Long userId, QuestionCreateRequest request) {
        return QuestionEntity.builder()
                .metaData(QuestionMetaData.create(request.title()))
                .content(request.content())
                .userId(userId)
                .likeCount(0)
                .build();
    }

    public void update(QuestionEditRequest request) {
        this.metaData = QuestionMetaData.create(request.title());
        this.content = request.content();
    }

}
