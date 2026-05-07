package com.likelion.sns.controller;

import com.likelion.sns.dto.CommentCreateRequest;
import com.likelion.sns.dto.CommentResponse;
import com.likelion.sns.dto.SuccessResponse;
import com.likelion.sns.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 API", description = "댓글 CRUD")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping
    public SuccessResponse<CommentResponse> createComment(
            @RequestBody @Valid CommentCreateRequest request
    ) {
        return SuccessResponse.created(commentService.createComment(request));
    }

    @Operation(summary = "게시글별 댓글 목록 조회")
    @GetMapping("/posts/{postId}")
    public SuccessResponse<List<CommentResponse>> getCommentsByPost(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Integer postId
    ) {
        return SuccessResponse.ok(commentService.getCommentList(postId));
    }

    @Operation(summary = "댓글 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "댓글 없음")
    })
    @GetMapping("/{commentId}")
    public SuccessResponse<CommentResponse> getComment(
            @Parameter(description = "댓글 ID", example = "1")
            @PathVariable Integer commentId
    ) {
        return SuccessResponse.ok(commentService.getComment(commentId));
    }
}