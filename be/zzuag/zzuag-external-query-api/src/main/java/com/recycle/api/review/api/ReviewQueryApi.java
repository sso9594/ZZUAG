package com.recycle.api.review.api;

import com.recycle.api.question.dto.response.ReviewPreviewDTO;
import com.recycle.api.review.dto.response.ReviewByUserResponse;
import com.recycle.api.review.dto.response.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Review Query", description = "리뷰 조회 API")
public interface ReviewQueryApi {
    @Operation(summary = "리뷰 조회", description = "리뷰를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable @Valid Long reviewId);

    @Operation(summary = "사용자별 좋아요 순 상위 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<Page<ReviewByUserResponse>> getReviewsByUserIdAndTopLikeCountByPagination(
            @PathVariable @Valid Long userId, @RequestParam int page, @RequestParam int size);

    @Operation(summary = "상위 10개 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<List<ReviewPreviewDTO>> getTopReviewedDTOs();

    @Operation(summary = "질문별 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<List<ReviewResponse>> getReviewsByQuestionId(@PathVariable @Valid Long questionId);

    @Operation(summary = "사용자별 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<List<ReviewResponse>> getReviewsByUserId(@PathVariable @Valid Long userId);
}
