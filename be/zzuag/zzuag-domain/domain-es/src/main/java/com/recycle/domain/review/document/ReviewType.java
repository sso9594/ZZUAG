package com.recycle.domain.review.document;

import lombok.Getter;

@Getter
public enum ReviewType {
    CODE("code"),
    LINE("line");

    private String type;

    ReviewType(String type) {
        this.type = type;
    }
}
