package com.zzuag.post_command_service.global.entity;

import com.zzuag.post_command_service.global.interfaces.Likeable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractPostEntity<T> extends BaseEntity implements Likeable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    protected String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Override
    public void incLikeCount() {
        this.likeCount++;
    }

    @Override
    public void decLikeCount() {
        this.likeCount--;
    }
}
