package com.zzuag.post_command_service.domain.question.service;

import com.zzuag.post_command_service.domain.question.dto.request.QuestionCreateRequest;
import com.zzuag.post_command_service.domain.question.dto.request.QuestionEditRequest;
import com.zzuag.post_command_service.domain.question.entity.QuestionEntity;
import com.zzuag.post_command_service.domain.question.event.QuestionLikeEvent;
import com.zzuag.post_command_service.domain.question.event.QuestionUnlikeEvent;
import com.zzuag.post_command_service.domain.question.persistence.repository.QuestionEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionEntityRepository questionEntityRepository;

    @Transactional
    public void createQuestion(QuestionCreateRequest request){
        QuestionEntity save = questionEntityRepository.save(QuestionEntity.create(request));
        log.info("Question created: {}", save);
    }

    @Transactional
    public void editQuestion(Long questionId, QuestionEditRequest request){
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        target.update(request);
        log.info("Question edited: {}", target);
    }

    @Transactional
    public void deleteQuestion(Long questionId){
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        target.delete();
        log.info("Question deleted: {}", target);
    }

    // 카운트 하나 올리려고 update 쿼리를 쌩으로 날리는게 맞는지 나중에 다시 생각해보기
    @Transactional
    @EventListener
    public void increaseLikeCount(QuestionLikeEvent event){
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(event.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        target.incLikeCount();
        questionEntityRepository.save(target);
    }

    @Transactional
    @EventListener
    public void decreaseLikeCount(QuestionUnlikeEvent event){
        QuestionEntity target = questionEntityRepository.findByIdAndIsDeletedFalse(event.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        target.decLikeCount();
        questionEntityRepository.save(target);
    }
}
