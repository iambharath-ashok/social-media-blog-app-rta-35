package com.bharath.learning.social_media_blog_app.service.impl;

import com.bharath.learning.social_media_blog_app.dto.CommentDto;
import com.bharath.learning.social_media_blog_app.entity.CommentEntity;
import com.bharath.learning.social_media_blog_app.entity.PostEntity;
import com.bharath.learning.social_media_blog_app.exceptions.CommentNotFoundException;
import com.bharath.learning.social_media_blog_app.exceptions.PostNotFoundException;
import com.bharath.learning.social_media_blog_app.repository.CommentRepository;
import com.bharath.learning.social_media_blog_app.repository.PostRepository;
import com.bharath.learning.social_media_blog_app.service.CommentService;
import com.bharath.learning.social_media_blog_app.utiils.CommentEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentEntityMapper commentEntityMapper;

    @Autowired
    private PostRepository postRepository; // Assuming you have a PostRepository to validate post existence

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
       // Fetch all comments for a specific post by postId

       List<CommentEntity> commentEntityList =  this.commentRepository.findByPostId(postId);
       if (commentEntityList != null && !commentEntityList.isEmpty()) {
         return  commentEntityList.stream()
                   .map(commentEntity ->
                           this.commentEntityMapper.mapEntityToDto(commentEntity))
                   .toList();
       }
       return List.of();
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {

        // Fetch a specific comment by postId and commentId
        // validate if post details exists by postId using postRepository
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));


        // fetch comment by commentId using commentRepository
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        // validate if comment actually belongs to the post
        if (commentEntity != null && postEntity != null ) {
            if (commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                return commentEntityMapper.mapEntityToDto(commentEntity);
            } else {
                throw new RuntimeException("Comment with id: " + commentId + " does not belong to Post with id: " + postId);
            }
        }
        throw new RuntimeException("Bad Request: Comment or Post not found");
    }

    @Override
    public CommentDto createCommentForPost(long postId, CommentDto commentDto) {
        // Create a new comment for a specific post
        // Convert CommentDto to CommentEntity
        CommentEntity commentEntity = this.commentEntityMapper.mapDtoToEntity(commentDto);

        // Fetch the PostEntity to ensure the post exists
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        // Attach the PostEntity to the CommentEntity
        commentEntity.setPostEntity(postEntity);

        // Save the CommentEntity to the database
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);
        return this.commentEntityMapper.mapEntityToDto(savedCommentEntity);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {
        // Fetch the PostEntity to ensure the post exists
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        //Fetch the CommentEntity to ensure the comment exists
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));


        // validate if comment actually belongs to the post
        if (commentEntity != null && postEntity != null ) {
            if (commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                // Update the comment entity with new values from commentDto
                commentEntity.setBody(commentDto.getBody());
                commentEntity.setUserName(commentDto.getUserName());
                commentEntity.setEmail(commentDto.getEmail());
            } else {
                throw new RuntimeException("Comment with id: " + commentId + " does not belong to Post with id: " + postId);
            }
        }


        // save the updated comment entity to the database
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);

        // return the updated comment dto
        return this.commentEntityMapper.mapEntityToDto(newlySavedCommentEntity);
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        // Fetch the PostEntity to ensure the post exists
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found with id: " + postId));

        //Fetch the CommentEntity to ensure the comment exists
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));


        // validate if comment actually belongs to the post
        if (commentEntity != null && postEntity != null ) {
            if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Comment with id: " + commentId + " does not belong to Post with id: " + postId);
            }
        }
        commentRepository.delete(commentEntity);
        return "Successfully deleted comment with id: " + commentId + " for post with id: " + postId;
    }

    @Override
    public String deleteAllCommentsOfPostsByPostId(long postId) {
        this.commentRepository.deleteByPostId(postId);
        return "Successfully deleted all comments for post with id: " + postId;
    }
}
