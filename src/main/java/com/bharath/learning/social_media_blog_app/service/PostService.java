package com.bharath.learning.social_media_blog_app.service;

import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.payload.PostResponse;

import java.util.List;

public interface PostService {

    // Get All the Posts
    List<PostDto> getAllPosts();

    // Get All the Posts with Pagination
    PostResponse getAllPosts(int pageNumber, int pageSize);

    // Get All the Posts with Pagination and Sorting
    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDirection);

    // Get Post by PostId
    PostDto getPostById(Long postId);

    // Create a New Post
    PostDto createPost(PostDto postDto);

    // Update an Existing Post
    PostDto updatePost(Long postId, PostDto postDto);

    // Delete a Post by PostId
    boolean deletePost(Long postId);
}
