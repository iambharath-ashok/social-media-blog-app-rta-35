package com.bharath.learning.social_media_blog_app.controller;


import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.payload.PostResponse;
import com.bharath.learning.social_media_blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
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
    public List<PostDto> getAllPosts() {
        // Logic to fetch all posts
        return postService.getAllPosts(); // Placeholder return statement
    }


    // Get Post by ID
    // GET /api/v1/posts/{postId}
    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        // Logic to fetch a post by ID
        return postService.getPostById(postId); // Placeholder return statement
    }

    // Create a new post
    // POST /api/v1/posts
    // @RequestMapping(method = RequestMethod.POST) - this is an alternative way to define a POST endpoint
    // @RequestBody is used to bind the request body to the method parameter
    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        // Logic to create a new post
        return postService.createPost(postDto); // Placeholder return statement
    }


    // Update an existing post
    // PUT /api/v1/posts/{postId}
    // @RequestMapping(method = RequestMethod.PUT) - this is an alternative way to define a PUT endpoint
    // @PathVariable is used to bind the path variable to the method parameter - in this case, postId
    // @RequestBody is used to bind the request body to the method parameter - in this case, postDto
    @PutMapping("/{postId}")
    public PostDto updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        // Logic to update an existing post
        return postService.updatePost(postId, postDto); // Placeholder return statement
    }


    // Delete a post by ID
    // DELETE /api/v1/posts/{postId}
    // @RequestMapping(method = RequestMethod.DELETE) - this is an alternative way to define a DELETE endpoint
    // @PathVariable is used to bind the path variable to the method parameter - in this case, postId
    // @DeleteMapping is used to map HTTP DELETE requests onto specific handler methods

    @DeleteMapping("/{postId}")
    public boolean deletePost(@PathVariable Long postId) {
        // Logic to delete a post by ID
        return postService.deletePost(postId); // Placeholder return statement
    }


}
