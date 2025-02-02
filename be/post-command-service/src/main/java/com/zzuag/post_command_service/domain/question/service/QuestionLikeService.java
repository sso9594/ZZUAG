package com.zzuag.post_command_service.domain.question.service;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionLikeCreateRequest;
import com.zzuag.post_command_service.domain.question.entity.QuestionEntity;
import com.zzuag.post_command_service.domain.question.entity.QuestionLikeEntity;
import com.zzuag.post_command_service.domain.question.event.QuestionLikeEvent;
import com.zzuag.post_command_service.domain.question.event.QuestionUnlikeEvent;
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
    public void likeQuestion(QuestionLikeCreateRequest request) {
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(request.questionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        questionLikeEntityRepository.save(QuestionLikeEntity.create(target, request));

        eventPublisher.publishEvent(QuestionLikeEvent.create(
                request.questionId(), request.userId()
        ));
    }

    @Transactional
    public void unlikeQuestion(QuestionLikeCreateRequest request) {
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(request.questionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        questionLikeEntityRepository.deleteByQuestionIdAndUserId(request.questionId(), request.userId());

        eventPublisher.publishEvent(QuestionUnlikeEvent.create(
                request.questionId(), request.userId()
        ));
    }
}
