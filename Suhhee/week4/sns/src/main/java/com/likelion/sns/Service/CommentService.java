package com.likelion.sns.Service;

import com.likelion.sns.Repository.CommentRepository;
import com.likelion.sns.Repository.PostRepository;
import com.likelion.sns.Repository.UserRepository;
import com.likelion.sns.dto.CommentResponseDto;
import com.likelion.sns.dto.CommentRequestDto;
import com.likelion.sns.entity.Comment;
import com.likelion.sns.entity.Post;
import com.likelion.sns.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 특정 게시글의 댓글 목록 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPostId(Integer postId) {
        // 게시글 존재여부 검토, 없을 경우 404
        postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글을 찾을 수 없습니다."));

        // 게시글에 달린 댓글들 가져오기
        List<Comment> comments = commentRepository.findByPost_PostId(postId);

        // List -> DTO
        List<CommentResponseDto> responseList = new ArrayList<>();
        for (Comment comment : comments) {
            responseList.add(new CommentResponseDto(comment));
        }

        return responseList;
    }

    // 댓글 상세 조회
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Integer commentId) {
        // 댓글 존재여부 검토, 없을 경우 404
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));

        return new CommentResponseDto(comment);
    }

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Integer postId, CommentRequestDto request) {
        // 게시글 존재여부 검토, 없을 경우 404
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 작성할 게시글을 찾을 수 없습니다."));

        // 유저 존재여부 검토, 없을 경우 404
        User user = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."));

        // 댓글 엔티티 생성, 데이터 세팅
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        // DB 저장
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
}