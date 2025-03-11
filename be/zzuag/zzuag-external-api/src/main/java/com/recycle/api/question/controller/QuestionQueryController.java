package com.recycle.api.question.controller;

import com.recycle.api.question.api.QuestionQueryApi;
import com.recycle.api.question.dto.response.QuestionByUserResponse;
import com.recycle.api.question.dto.response.QuestionResponse;
import com.recycle.api.question.usecase.QuestionQueryUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionQueryController implements QuestionQueryApi {
    private final QuestionQueryUsecase questionQueryUsecase;

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable @Valid Long questionId) {
        return ResponseEntity.ok(
                questionQueryUsecase.getQuestionById(questionId)
        );
    }

    @GetMapping("/top10")
    public ResponseEntity<List<QuestionResponse>> findQuestionsByTop10Reviewed() {
        return ResponseEntity.ok(
                questionQueryUsecase.findQuestionsByTop10Reviewed()
        );
    }

    @GetMapping("/user/{userId}/top-like")
    public ResponseEntity<Page<QuestionByUserResponse>> getQuestionsByUserIdAndTopLikeCountByPagination(
            @PathVariable @Valid Long userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(
                questionQueryUsecase.getQuestionsByUserIdAndTopLikeCountByPagination(userId, page, size)
        );
    }


}
