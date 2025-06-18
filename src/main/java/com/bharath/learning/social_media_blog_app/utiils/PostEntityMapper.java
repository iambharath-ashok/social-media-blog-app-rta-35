package com.bharath.learning.social_media_blog_app.utiils;

import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.entity.PostEntity;
import org.springframework.stereotype.Component;


@Component
public class PostEntityMapper {

    public PostDto mapPostEntityToPostDto(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }

        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());

        return postDto;
    }

    public PostEntity mapPostDtoToPostEntity(PostDto postDto) {
        if (postDto == null) {
            return null;
        }

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());

        return postEntity;
    }
}
