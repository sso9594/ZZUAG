package com.recycle.api.review.controller;

import com.recycle.api.question.dto.response.ReviewPreviewDTO;
import com.recycle.api.review.api.ReviewQueryApi;
import com.recycle.api.review.dto.response.ReviewByUserResponse;
import com.recycle.api.review.dto.response.ReviewResponse;
import com.recycle.api.review.usecase.ReviewQueryUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/api/v1/review")
@RequiredArgsConstructor
@Validated
public class ReviewQueryController implements ReviewQueryApi {
    private final ReviewQueryUsecase reviewQueryUsecase;

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable @Valid Long reviewId) {
        return ResponseEntity.ok(
                reviewQueryUsecase.getReviewById(reviewId)
        );
    }

    @GetMapping("/user/{userId}/top-like")
    public ResponseEntity<Page<ReviewByUserResponse>> getReviewsByUserIdAndTopLikeCountByPagination(
            @PathVariable @Valid Long userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(
                reviewQueryUsecase.getReviewsByUserIdAndTopLikeCountByPagination(userId, page, size)
        );
    }

    @GetMapping("/top-reviewed")
    public ResponseEntity<List<ReviewPreviewDTO>> getTopReviewedDTOs() {
        return ResponseEntity.ok(
                reviewQueryUsecase.getTopReviewedDTOs()
        );
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByQuestionId(@PathVariable @Valid Long questionId) {
        return ResponseEntity.ok(
                reviewQueryUsecase.getReviewsByQuestionId(questionId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByUserId(@PathVariable @Valid Long userId) {
        return ResponseEntity.ok(
                reviewQueryUsecase.getReviewsByUserId(userId)
        );
    }
}
