package com.recycle.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractPostDocument extends BaseDocument{
    @Id
    private Long id;

    @Field(type = FieldType.Text, store = true, name = "content")
    protected String content;

    @Field(type = FieldType.Long, store = true, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Integer, index = false, store = true, name = "like_count")
    private int likeCount;
}
