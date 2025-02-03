package com.zzuag.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
public class User extends BaseUserEntity {

    @Embedded
    private UserCertification userCertification;

    @Embedded
    private UserInfo userInfo;

    @Column(name = "user_role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public static User signUp(String email, String password, UserInfo userInfo, UserRole userRole) {
        return User.builder()
                .userCertification(UserCertification.create(email, password))
                .userInfo(userInfo)
                .userRole(userRole)
                .build();
    }

    public void updateUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}