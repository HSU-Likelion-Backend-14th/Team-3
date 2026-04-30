package com.likelion.sns.repository;

import com.likelion.sns.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPost_PostId(Integer postId);

}