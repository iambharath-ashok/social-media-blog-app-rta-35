package com.bharath.learning.social_media_blog_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Comment")
public class CommentDto {

    private long id;
    @NotEmpty(message = "User name cannot be empty")
    private String userName;
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotEmpty(message = "Comment body cannot be empty")
    private String body;
}
