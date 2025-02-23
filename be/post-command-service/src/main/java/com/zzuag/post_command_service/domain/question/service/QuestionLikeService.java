package com.zzuag.post_command_service.domain.question.service;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionLikeRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionUnlikeRequest;
import com.zzuag.post_command_service.domain.question.entity.QuestionEntity;
import com.zzuag.post_command_service.domain.question.entity.QuestionLikeEntity;
import com.zzuag.post_command_service.domain.question.service.event.QuestionLikeEvent;
import com.zzuag.post_command_service.domain.question.service.event.QuestionUnlikeEvent;
import com.zzuag.post_command_service.domain.question.persistence.repository.QuestionEntityRepository;
import com.zzuag.post_command_service.domain.question.persistence.repository.QuestionLikeEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionLikeService {

    private final ApplicationEventPublisher eventPublisher;

    private final QuestionEntityRepository questionEntityRepository;
    private final QuestionLikeEntityRepository questionLikeEntityRepository;

    @Transactional
    public void likeQuestion(Long userId, QuestionLikeRequest request) {
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(request.questionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        if (target.isDeleted()) {
            throw new IllegalArgumentException("Question is deleted");
        }
        questionLikeEntityRepository.save(QuestionLikeEntity.create(target, userId));

        eventPublisher.publishEvent(QuestionLikeEvent.create(
                request.questionId(), userId
        ));
    }

    @Transactional
    public void unlikeQuestion(Long userId, QuestionUnlikeRequest request) {
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(request.questionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        if (target.isDeleted()) {
            throw new IllegalArgumentException("Question is deleted");
        }
        QuestionLikeEntity targetLike = questionLikeEntityRepository.findByQuestionIdAndUserId(request.questionId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("Like not found"));
        if (!targetLike.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User not authorized");
        }
        questionLikeEntityRepository.deleteByQuestionIdAndUserId(request.questionId(), userId);

        eventPublisher.publishEvent(QuestionUnlikeEvent.create(
                request.questionId(), userId
        ));
    }
}
