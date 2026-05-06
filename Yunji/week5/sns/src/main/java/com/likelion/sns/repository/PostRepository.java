package com.likelion.sns.repository;

import com.likelion.sns.entity.Post;
import com.likelion.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    // 1. 요구사항: 특정 키워드가 포함된 게시글 검색
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
    List<Post> findPostsByKeyword(@Param("keyword") String keyword);

    // 2. 요구사항: 특정 사용자의 게시글 중 최신 5개
    List<Post> findTop5ByUserOrderByCreatedAtDesc(User user);

    // 3. 요구사항: 조회수가 많은 순서로 정렬
    List<Post> findAllByOrderByViewCountDesc();
}
