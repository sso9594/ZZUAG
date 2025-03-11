package com.recycle.api.question.controller;

import com.recycle.api.question.api.QuestionQueryApi;
import com.recycle.api.question.usecase.QuestionQueryUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/api/v1/question")
@RequiredArgsConstructor
@Validated
public class QuestionQueryController implements QuestionQueryApi {
    private final QuestionQueryUsecase questionQueryUsecase;

    @GetMapping("/{questionId}")
    public void getQuestionById(@PathVariable @Valid Long questionId) {
        questionQueryUsecase.getQuestionById(questionId);
    }

    @GetMapping("/top10")
    public void findQuestionsByTop10Reviewed() {
        questionQueryUsecase.findQuestionsByTop10Reviewed();
    }

    @GetMapping("/user/{userId}/top-like")
    public void getQuestionsByUserIdAndTopLikeCountByPagination(@PathVariable @Valid Long userId, @RequestParam int page, @RequestParam int size) {
        questionQueryUsecase.getQuestionsByUserIdAndTopLikeCountByPagination(userId, page, size);
    }


}
