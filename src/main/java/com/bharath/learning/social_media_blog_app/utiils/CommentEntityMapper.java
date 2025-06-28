package com.bharath.learning.social_media_blog_app.utiils;

import com.bharath.learning.social_media_blog_app.dto.CommentDto;
import com.bharath.learning.social_media_blog_app.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CommentDto mapEntityToDto(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }
        return modelMapper.map(commentEntity, CommentDto.class);
    }

    public CommentEntity mapDtoToEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        return modelMapper.map(commentDto, CommentEntity.class);
    }
}
