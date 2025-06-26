package com.bharath.learning.social_media_blog_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Data Transfer Object for Post")
public class PostDto {

    @Schema(description = "Unique identifier for the post", example = "1")
    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, message = "Post title must be at least 3 characters long")
    @Schema(description = "Title of the post", example = "My First Post")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 5, message = "Post description must be at least 5 characters long")
    @Schema(description = "Description of the post", example = "This is a brief description of my first post")
    private String description;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 7, message = "Post content must be at least 7 characters long")
    @Schema(description = "Content of the post", example = "This is the content of my first post. It contains detailed information about the topic.")
    private String content;
}
