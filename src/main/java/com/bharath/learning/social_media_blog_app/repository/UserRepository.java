package com.bharath.learning.social_media_blog_app.repository;

import com.bharath.learning.social_media_blog_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
