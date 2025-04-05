package com.recycle.api.review.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Review Favorite Command", description = "리뷰 즐겨찾기 명령 API")
public interface ReviewFavoriteCommandApi {
    @Operation(summary = "리뷰 즐겨찾기 추가", description = "리뷰를 즐겨찾기에 추가합니다.")
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
    public ResponseEntity<Void> createReviewFavorite (Long reviewId);

    @Operation(summary = "리뷰 즐겨찾기 삭제", description = "리뷰를 즐겨찾기에서 삭제합니다.")
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
    public ResponseEntity<Void> deleteReviewFavorite (Long reviewId);
}
