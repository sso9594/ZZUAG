package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.QuestionRequestEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRequestRepository extends JpaRepository<QuestionRequestEntity, Long> {
	List<QuestionRequestEntity> findAllByReceiverId(Long receiverId);
}
