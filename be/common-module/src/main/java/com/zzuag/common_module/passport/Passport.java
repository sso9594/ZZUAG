package com.zzuag.common_module.passport;

public record Passport(
        Long userId,
        String username,
        String email,
        boolean isDeleted
) {
}
