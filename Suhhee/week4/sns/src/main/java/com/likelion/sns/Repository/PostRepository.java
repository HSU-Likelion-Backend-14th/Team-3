package com.likelion.sns.Repository;

import com.likelion.sns.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE p.content LIKE %:keyword%")
    List<Post> searchByContent(@Param("keyword") String keyword);

    List<Post> findTop5ByUser_UserIdOrderByCreatedAtDesc(Integer userId);

    List<Post> findAllByOrderByViewCountDesc();
}
