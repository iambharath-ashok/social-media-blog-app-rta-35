package com.bharath.learning.social_media_blog_app.service.impl;

import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.entity.PostEntity;
import com.bharath.learning.social_media_blog_app.repository.PostRepository;
import com.bharath.learning.social_media_blog_app.service.PostService;
import com.bharath.learning.social_media_blog_app.utiils.PostEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEntityMapper postEntityMapper;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();

        if (postEntities != null) {
          return  postEntities.stream()
                    .map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                    .toList();
        }
        return List.of();
    }

    @Override
    public PostDto getPostById(Long postId) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            PostDto postDto = postEntityMapper.mapPostEntityToPostDto(postEntity);
            return postDto;
        }
        throw new RuntimeException("Post not found with ID: " + postId);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
       PostEntity postEntityToSave = postEntityMapper.mapPostDtoToPostEntity(postDto);
       PostEntity savedEntity = postRepository.save(postEntityToSave);
       return postEntityMapper.mapPostEntityToPostDto(savedEntity);
    }

    @Override
    public PostDto updatePost(Long postIdToBeUpdated, PostDto postDto) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postIdToBeUpdated);

        if (postEntityOptional.isPresent()) {
           PostEntity postEntityToBeUpdated =  postEntityOptional.get();
           postEntityToBeUpdated.setTitle(postDto.getTitle());
           postEntityToBeUpdated.setDescription(postDto.getDescription());
           postEntityToBeUpdated.setContent(postDto.getContent());
           PostEntity updatedPostEntity = postRepository.save(postEntityToBeUpdated);
           return postEntityMapper.mapPostEntityToPostDto(updatedPostEntity);
        } else {
            throw new RuntimeException("Post not found with ID: " + postIdToBeUpdated);
        }
    }

    @Override
    public boolean deletePost(Long postId) {
       // postRepository.deleteById(postId);
        Optional<PostEntity> postEntityOptional = postRepository.findById(postId);
        if (postEntityOptional.isPresent()) {
           PostEntity postEntityToBeDeleted =  postEntityOptional.get();
           postRepository.delete(postEntityToBeDeleted);
           return true;
        }
        throw new RuntimeException("Post not found with ID: " + postId);
    }
}
