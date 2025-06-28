package com.bharath.learning.social_media_blog_app.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a blog post in the Social Media Blog Application")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, message = "Post title must be at least 3 characters long")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, message = "Post description must be at least 5 characters long")
    @Column(name = "description")
    private String description;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 7, message = "Post content must be at least 7 characters long")
    @Column(name = "content")
    private String content;

    // OneToMany Relationship
    // Single Posts can have multiple comments
    @OneToMany(mappedBy = "postEntity")
    private Set<CommentEntity> comments = new HashSet<>();

}
