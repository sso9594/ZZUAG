package com.recycle.domain.review.document;

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
@Document(indexName = "review")
public class Review extends AbstractPostDocument {
    @Field(type = FieldType.Long, name = "question_id")
    private Long question;

    @Field(type = FieldType.Object, name = "start_point")
    private ReviewPoint startPoint;

    @Field(type = FieldType.Object, name = "end_point")
    private ReviewPoint endPoint;

    @Field(type = FieldType.Keyword, name = "tag")
    private ReviewType tag;
}
