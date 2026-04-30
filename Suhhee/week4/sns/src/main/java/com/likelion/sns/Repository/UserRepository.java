package com.likelion.sns.Repository;

import com.likelion.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
