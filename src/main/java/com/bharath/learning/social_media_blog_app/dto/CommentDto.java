package com.bharath.learning.social_media_blog_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Comment")
public class CommentDto {

    private long id;
    private String userName;
    private String email;
    private String body;
}
