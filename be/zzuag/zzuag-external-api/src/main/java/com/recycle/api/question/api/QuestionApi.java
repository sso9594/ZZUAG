package com.recycle.api.question.api;

import com.recycle.api.question.dto.QuestionCreateRequest;
import com.recycle.api.question.dto.QuestionUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Question", description = "질문 API")
public interface QuestionApi {
    @Operation(summary = "질문 생성", description = "질문을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "성공", value = """
                            {
                                "status": "200"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<Void> createQuestion(@RequestBody @Valid QuestionCreateRequest request);

    @Operation(summary = "질문 수정", description = "질문을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "성공", value = """
                            {
                                "status": "200"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<Void> updateQuestion(Long questionId, @RequestBody @Valid QuestionUpdateRequest request);

    @Operation(summary = "질문 삭제", description = "질문을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(name = "성공", value = """
                            {
                                "status": "200"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<Void> deleteQuestion(Long questionId);
}
