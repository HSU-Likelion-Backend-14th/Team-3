package com.likelion.sns.controller;

import com.likelion.sns.dto.CommentCreateRequest;
import com.likelion.sns.dto.CommentResponse;
import com.likelion.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Integer postId) {

        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // 댓글 상세 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Integer commentId) {

        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    // 댓글 작성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Integer postId, @RequestBody CommentCreateRequest request) {
        CommentResponse response = commentService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }
}
