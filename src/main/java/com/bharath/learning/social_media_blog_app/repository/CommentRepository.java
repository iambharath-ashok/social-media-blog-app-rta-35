package com.bharath.learning.social_media_blog_app.repository;

import com.bharath.learning.social_media_blog_app.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM Comments WHERE post_id = ?1", nativeQuery = true)
    List<CommentEntity> findByPostId(long postId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Comments WHERE post_id = ?1", nativeQuery = true)
    void deleteByPostId(long postId);
}
