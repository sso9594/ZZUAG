package com.recycle.domain.question.document;

import com.recycle.domain.AbstractPostDocument;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Document(indexName = "question-search")
public class Question extends AbstractPostDocument {
    @Field(type = FieldType.Text, store = true, name = "title")
    private String title;

    @Field(type = FieldType.Integer, index = false, store = true, name = "review_count")
    private int reviewCount;
}
