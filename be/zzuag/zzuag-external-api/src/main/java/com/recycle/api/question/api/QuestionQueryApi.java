package com.recycle.api.question.api;

import com.recycle.api.question.dto.response.QuestionByUserResponse;
import com.recycle.api.question.dto.response.QuestionResponse;
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

@Tag(name = "QuestionQueryApi", description = "질문 조회 API")
public interface QuestionQueryApi {
    @Operation(summary = "질문 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable @Valid Long questionId);

    @Operation(summary = "상위 10개 질문 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    public ResponseEntity<List<QuestionResponse>> findQuestionsByTop10Reviewed();

    @Operation(summary = "사용자별 좋아요 순 상위 질문 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공")
    })
    public ResponseEntity<Page<QuestionByUserResponse>> getQuestionsByUserIdAndTopLikeCountByPagination(
            @PathVariable @Valid Long userId, @RequestParam int page, @RequestParam int size);
}