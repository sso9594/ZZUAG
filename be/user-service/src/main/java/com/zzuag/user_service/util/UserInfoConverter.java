package com.zzuag.user_service.util;

import com.zzuag.user_service.dto.response.UserInfoResponse;
import com.zzuag.user_service.entity.User;

public class UserInfoConverter {
    public static UserInfoResponse from(User user){
        return UserInfoResponse.builder()
                .userInfo(user.getUserInfo())
                .build();
    }
}