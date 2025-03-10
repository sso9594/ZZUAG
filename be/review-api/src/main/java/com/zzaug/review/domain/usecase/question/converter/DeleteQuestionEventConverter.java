package com.zzaug.review.domain.usecase.question.converter;

import com.zzaug.review.domain.event.question.DeleteQuestionEvent;
import com.zzaug.review.entity.question.QuestionEntity;
import java.sql.Timestamp;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuestionEventConverter {
	public static DeleteQuestionEvent from(QuestionEntity source) {
		return DeleteQuestionEvent.builder()
				.questionId(source.getQuestionId())
				.author(source.getAuthor())
				.authorId(source.getAuthorId())
				.deletedAt(source.getDeletedAt())
				.eventAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime())
				.build();
	}
}
