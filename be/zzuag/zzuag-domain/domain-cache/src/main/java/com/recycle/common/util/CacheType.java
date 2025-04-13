package com.recycle.common.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {
    SEARCH_QUESTION("questionsearch", 5, 1,5000);

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int expiredAfterAccess;
    private final int maximumSize;
}
