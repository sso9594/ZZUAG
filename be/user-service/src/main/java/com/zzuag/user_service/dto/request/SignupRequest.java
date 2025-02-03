package com.zzuag.user_service.dto.request;

import com.zzuag.user_service.entity.UserCertification;
import com.zzuag.user_service.entity.UserInfo;
import com.zzuag.user_service.entity.UserRole;

public record SignupRequest(
        UserCertification userCertification,
        UserInfo userInfo,
        UserRole userRole
) {
}
