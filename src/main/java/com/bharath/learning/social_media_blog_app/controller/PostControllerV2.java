package com.bharath.learning.social_media_blog_app.controller;

import com.bharath.learning.social_media_blog_app.payload.PostResponse;
import com.bharath.learning.social_media_blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/posts")
public class PostControllerV2 {

    @Autowired
    private PostService postService;
    // This controller will handle requests for version 2 of the Post API

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
                                    @RequestParam(value= "pageSize", defaultValue = "0", required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
        // Logic to fetch all posts with pagination and sorting
        return postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection); // Placeholder return statement
    }
}
