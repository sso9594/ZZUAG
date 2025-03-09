package com.recycle.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractPostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    protected String content;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    public void incLikeCount() {
        this.likeCount++;
    }

    public void decLikeCount() {
        this.likeCount--;
    }
}
