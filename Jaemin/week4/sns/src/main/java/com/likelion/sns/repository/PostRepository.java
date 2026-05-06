package com.likelion.sns.repository;

import com.likelion.sns.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p FROM Post p WHERE p.user.userId = :userId")
    List<Post> findByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT * FROM posts WHERE view_count > ?1", nativeQuery = true)
    List<Post> findPoupularPosts(Integer minViews);

    // 내용에 키워드가 포함된 키워드 검색
    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
    List<Post> findByKeyword(@Param("keyword") String keyword);

    // 특정 사용자의 게시글 중 최신 5개
    List<Post> findTop5ByUserIdOrderByCreatedAtDesc(Integer userId);

    // 조회수가 많은 순서로 정렬
    List<Post> findByOrderByViewCountDesc();
}
