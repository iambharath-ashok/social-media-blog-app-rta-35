package com.bharath.learning.social_media_blog_app.controller;


import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.payload.ErrorDetails;
import com.bharath.learning.social_media_blog_app.payload.PostResponse;
import com.bharath.learning.social_media_blog_app.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Posts Management", description = "API's for creating, updating, retrieving and deleting posts in the Social Media Blog Application")
public class PostController {

    @Autowired
    private PostService postService;

    // This is where you will define your endpoints for CRUD operations on posts

    // For example: Get all posts
    // GET /api/v1/posts
    // @RequestMapping(method = RequestMethod.GET) // This is an alternative way to define a GET endpoint
    // @GetMapping is a shortcut for @RequestMapping(method = RequestMethod.GET)
    // GetMapping is used to map HTTP GET requests onto specific handler methods

    @GetMapping
    @Operation(summary = "Get all posts", description = "Fetches all posts from the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved posts"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))
            ),


    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<PostDto> getAllPosts() {
        // Logic to fetch all posts
        return postService.getAllPosts(); // Placeholder return statement
    }


    // Get Post by ID
    // GET /api/v1/posts/{postId}
    @GetMapping("/{postId}")
    @Operation(summary = "Get post by ID", description = "Returns a single specific post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved post"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))
            )
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostDto getPostById(
           @Parameter(description = "ID of the post to retrieve", required = true) @PathVariable Long postId) {
        // Logic to fetch a post by ID
        return postService.getPostById(postId); // Placeholder return statement
    }

    // Create a new post
    // POST /api/v1/posts
    // @RequestMapping(method = RequestMethod.POST) - this is an alternative way to define a POST endpoint
    // @RequestBody is used to bind the request body to the method parameter
    @PostMapping
    @Operation(summary = "Create a new post", description = "Creates a new post in the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public PostDto createPost(
            @Parameter(description = "Post payload data to create a new post", required = true)
            @Valid @RequestBody PostDto postDto) {
        // Logic to create a new post
        return postService.createPost(postDto); // Placeholder return statement
    }


    // Update an existing post
    // PUT /api/v1/posts/{postId}
    // @RequestMapping(method = RequestMethod.PUT) - this is an alternative way to define a PUT endpoint
    // @PathVariable is used to bind the path variable to the method parameter - in this case, postId
    // @RequestBody is used to bind the request body to the method parameter - in this case, postDto
    @PutMapping("/{postId}")
    @Operation(summary = "Update an existing post", description = "Updates an existing post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public PostDto updatePost(
            @Parameter(description = "ID of the post to update", required = true)
            @PathVariable
            Long postId,
            @Parameter(description = "Post payload data to update", required = true)
            @Valid @RequestBody PostDto postDto) {
        // Logic to update an existing post
        return postService.updatePost(postId, postDto); // Placeholder return statement
    }


    // Delete a post by ID
    // DELETE /api/v1/posts/{postId}
    // @RequestMapping(method = RequestMethod.DELETE) - this is an alternative way to define a DELETE endpoint
    // @PathVariable is used to bind the path variable to the method parameter - in this case, postId
    // @DeleteMapping is used to map HTTP DELETE requests onto specific handler methods

    @DeleteMapping("/{postId}")
    @Operation(summary = "Delete a post", description = "Deletes a post by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorDetails.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deletePost(
            @Parameter(description = "ID of the post to delete", required = true)
            @PathVariable Long postId) {
        // Logic to delete a post by ID
        return postService.deletePost(postId); // Placeholder return statement
    }


}
