package com.zzuag.user_service.dto.request;

import com.zzuag.user_service.entity.UserInfo;

public record EditProfileRequest(
        UserInfo userInfo
) {
}
