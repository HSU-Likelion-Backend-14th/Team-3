package com.likelion.sns.service;

import com.likelion.sns.dto.CommentCreateRequest;
import com.likelion.sns.dto.CommentResponse;
import com.likelion.sns.entity.Comment;
import com.likelion.sns.entity.Post;
import com.likelion.sns.entity.User;
import com.likelion.sns.repository.CommentRepository;
import com.likelion.sns.repository.PostRepository;
import com.likelion.sns.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 작성
    public CommentResponse createComment(CommentCreateRequest request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setPost(post);
        comment.setUser(user);

        Comment saved = commentRepository.save(comment);
        return convertToResponse(saved);
    }

    // 게시글별 댓글 목록 조회
    public List<CommentResponse> getCommentList(Integer postId) {
        return commentRepository.findByPost_PostId(postId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 댓글 상세 조회
    public CommentResponse getComment(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        return convertToResponse(comment);
    }

    //의 필드명(userId, nickname 등)에 맞춘 변환
    private CommentResponse convertToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setUserId(comment.getUser().getUserId());
        response.setNickname(comment.getUser().getNickname());
        response.setCreatedAt(comment.getCreatedAt());
        return response;
    }
}