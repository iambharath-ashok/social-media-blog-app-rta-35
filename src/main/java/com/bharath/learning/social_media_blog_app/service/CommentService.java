package com.bharath.learning.social_media_blog_app.service;

import com.bharath.learning.social_media_blog_app.dto.CommentDto;

import java.util.List;

public interface CommentService {

    // Fetch Operation
    // Get All Comments By PostId
    List<CommentDto> getAllCommentsByPostId(long postId);


    // Get Specific Comment By PostId and CommentId
    CommentDto getCommentByPostIdAndCommentId(long postId, long commentId);

    // Create operation
    // Create Comment for Particular Post
    CommentDto createCommentForPost(long postId, CommentDto commentDto);

    // Update operation
    // Update Specific Comment for Particular Post by PostId and CommentId
    CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto);


    // Delete Operations
    // Delete Comment By PostId and CommentId
    String deleteCommentByPostIdAndCommentId(long postId, long commentId);

    // Delete All Comments of Particular Posts
    String deleteAllCommentsOfPostsByPostId(long postId);

}
