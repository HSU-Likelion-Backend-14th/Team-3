package com.likelion.sns.Repository;

import com.likelion.sns.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    // 특정 게시글의 댓글 목록
    List<Comment> findByPost_PostId(Integer postId);
}
