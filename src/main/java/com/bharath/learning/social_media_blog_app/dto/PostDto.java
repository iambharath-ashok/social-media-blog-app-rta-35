package com.bharath.learning.social_media_blog_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {

    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, message = "Post title must be at least 3 characters long")
    private String title;
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, message = "Post description must be at least 5 characters long")
    private String description;
    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 7, message = "Post content must be at least 7 characters long")
    private String content;
}
