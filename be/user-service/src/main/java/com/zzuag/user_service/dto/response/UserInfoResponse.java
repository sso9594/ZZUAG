package com.zzuag.user_service.dto.response;

import com.zzuag.user_service.entity.UserInfo;
import lombok.Builder;

@Builder
public record UserInfoResponse(
        UserInfo userInfo
) {
}
