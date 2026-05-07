package com.likelion.sns.controller;

import com.likelion.sns.dto.CommentCreateRequest;
import com.likelion.sns.dto.CommentResponse;
import com.likelion.sns.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Comment API", description = "댓글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 댓글 목록 조회
    @Operation(
            summary = "특정 게시글의 댓글 목록 조회",
            description = "게시글 ID를 통해 해당 게시글에 달린 댓글 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글이 존재하지 않음")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Integer postId
    ) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // 댓글 상세 조회
    @Operation(
            summary = "댓글 상세 조회",
            description = "댓글 ID를 통해 해당 댓글의 상세 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "댓글이 존재하지 않음")
    })
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @Parameter(description = "댓글 ID", example = "1")
            @PathVariable Integer commentId
    ) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    // 댓글 작성
    @Operation(
            summary = "댓글 작성",
            description = "게시글에 새로운 댓글을 작성합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 또는 사용자가 존재하지 않음")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Integer postId,
            @RequestBody CommentCreateRequest request
    ) {
        CommentResponse response = commentService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }
}
