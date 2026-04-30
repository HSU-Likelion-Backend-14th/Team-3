package com.likelion.sns.Controller;

import com.likelion.sns.dto.ApiResponse;
import com.likelion.sns.dto.CommentResponseDto;
import com.likelion.sns.dto.CommentRequestDto;
import com.likelion.sns.Service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 API", description = "댓글 조회 및 작성")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 목록 조회
    @Operation(summary = "특정 게시글의 댓글 목록 조회", description = "특정 게시글 ID에 해당하는 모든 댓글을 조회합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "해당 게시글을 찾을 수 없습니다.")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<List<CommentResponseDto>>> getComments(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Integer postId) {

        List<CommentResponseDto> result = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(ApiResponse.ok(result, "댓글 목록을 성공적으로 조회했습니다."));
    }

    // 댓글 하나 상세 조회
    @Operation(summary = "댓글 하나 상세 조회", description = "댓글 ID로 댓글의 상세 정보를 조회합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "해당 댓글을 찾을 수 없습니다.")
    })
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> getComment(
            @Parameter(description = "댓글 ID", example = "1") @PathVariable Integer commentId) {

        CommentResponseDto result = commentService.getCommentById(commentId);
        return ResponseEntity.ok(ApiResponse.ok(result, "댓글을 성공적으로 조회했습니다."));
    }

    // 댓글 작성
    @Operation(summary = "댓글 작성", description = "특정 게시글에 댓글을 작성합니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "작성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 내용을 작성해주세요."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "게시글 또는 유저를 찾을 수 없습니다.")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Integer postId,
            @Valid @RequestBody CommentRequestDto request) {

        CommentResponseDto result = commentService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(result, "댓글이 성공적으로 작성되었습니다."));
    }
}