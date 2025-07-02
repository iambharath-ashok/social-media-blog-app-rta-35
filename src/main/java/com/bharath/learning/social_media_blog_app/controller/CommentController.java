package com.bharath.learning.social_media_blog_app.controller;

import com.bharath.learning.social_media_blog_app.dto.CommentDto;
import com.bharath.learning.social_media_blog_app.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Comments Management", description = "API's for managing comments in the Social Media Blog Application")
public class CommentController {

    @Autowired
    private CommentService commentService;


    // Define endpoints for comment operations here
    // For example: Get all comments, Add a comment, Update a comment, Delete a comment, etc.

    // GET /api/v1/posts/{postId}/comments
    // Get all Comments by PostId
    // This endpoint retrieves all comments associated with a specific post by its ID.
    @Operation(summary = "Get all comments by PostId",
            description = "Fetches all comments associated with a specific post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(
            @Parameter(description = "ID of the post to fetch comments from", required = true)
            @PathVariable long postId) {
        return commentService.getAllCommentsByPostId(postId);
    }

    // GET /api/v1/posts/{postId}/comments/{commentId}
    // Get a specific comment by PostId and CommentId
    // This endpoint retrieves a specific comment associated with a post by both the post ID and the comment ID.
    @Operation(summary = "Get a specific comment by PostId and CommentId",
            description = "Fetches a specific comment associated with a post by both the post ID and the comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved comment"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post to which the comment belongs", required = true)
            @PathVariable long postId,
            @Parameter(description = "ID of the comment to fetch", required = true)
            @PathVariable long commentId) {
        return commentService.getCommentByPostIdAndCommentId(postId, commentId);
    }


    // POST /api/v1/posts/{postId}/comments
    // Create a new comment for a specific post
    // This endpoint allows users to create a new comment for a specific post by its ID.
    @Operation(summary = "Create a new comment for a specific post",
            description = "Adds a new comment to a specific post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created comment"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/posts/{postId}/comments")
    public CommentDto createCommentForPost(
            @Parameter(description = "ID of the post to add a comment to", required = true)
            @PathVariable long postId,
            @Parameter(description = "Comment data to be added", required = true)
            @RequestBody CommentDto commentDto) {
        return commentService.createCommentForPost(postId, commentDto);
    }


    // PUT /api/v1/posts/{postId}/comments/{commentId}
    // Update a specific comment for a post by PostId and CommentId
    // This endpoint allows users to update an existing comment associated with a specific post by both the post ID and the comment ID.

    @Operation(summary = "Update a specific comment for a post by PostId and CommentId",
            description = "Updates an existing comment associated with a specific post by both the post ID and the comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated comment"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto updateCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post to which the comment belongs", required = true)
            @PathVariable long postId,
            @Parameter(description = "ID of the comment to update", required = true)
            @PathVariable long commentId,
            @Parameter(description = "Updated comment data", required = true)
            @RequestBody CommentDto commentDto) {
        return commentService.updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
    }

    // DELETE /api/v1/posts/{postId}/comments/{commentId}
    // Delete a specific comment by PostId and CommentId
    // This endpoint allows users to delete a specific comment associated with a post by both the post ID and the comment ID.

    @Operation(summary = "Delete a specific comment by PostId and CommentId",
            description = "Deletes a specific comment associated with a post by both the post ID and the comment ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted comment"),
            @ApiResponse(responseCode = "404", description = "Post or comment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String deleteCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post to which the comment belongs", required = true)
            @PathVariable long postId,
            @Parameter(description = "ID of the comment to delete", required = true)
            @PathVariable long commentId) {
        return commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
    }

    // DELETE /api/v1/posts/{postId}/comments
    // Delete all comments of a specific post by PostId
    // This endpoint allows users to delete all comments associated with a specific post by its ID.

    @Operation(summary = "Delete all comments of a specific post by PostId",
            description = "Deletes all comments associated with a specific post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted all comments"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/posts/{postId}/comments")
    public String deleteAllCommentsOfPostsByPostId(
            @Parameter(description = "ID of the post to delete all comments from", required = true)
            @PathVariable long postId) {
        return commentService.deleteAllCommentsOfPostsByPostId(postId);
    }

}
