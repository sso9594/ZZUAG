package com.zzuag.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {
    @Column(name = "nickname", nullable = false, length = 15)
    private String nickname;

    @Column(name = "phnum", nullable = false, length = 20)
    private String phnum;
}
