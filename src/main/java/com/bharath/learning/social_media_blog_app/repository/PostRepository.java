package com.bharath.learning.social_media_blog_app.repository;

import com.bharath.learning.social_media_blog_app.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
