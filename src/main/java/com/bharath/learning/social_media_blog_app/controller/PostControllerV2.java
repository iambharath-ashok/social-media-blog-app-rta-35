package com.bharath.learning.social_media_blog_app.controller;

import com.bharath.learning.social_media_blog_app.payload.PostResponse;
import com.bharath.learning.social_media_blog_app.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/posts")
@Tag(name = "Posts Management V2", description = "API's for using Pagation and Sorting in Post Management")
public class PostControllerV2 {

    @Autowired
    private PostService postService;
    // This controller will handle requests for version 2 of the Post API

    @GetMapping
    @Operation(summary = "Get all posts with pagination and sorting",
               description = "Fetches all posts with optional pagination and sorting parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved posts"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PostResponse getAllPosts(
            @Parameter(description = "Page number for pagination, starting from 0")
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
                                    @Parameter(description = "Number of posts per page for pagination")
                                    @RequestParam(value= "pageSize", defaultValue = "1", required = false) int pageSize,
                                    @Parameter(description = "Field to sort the posts by, e.g., 'id', 'title'")
                                    @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                    @Parameter(description = "Direction to sort the posts, either 'asc' or 'desc'")
                                    @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
        // Logic to fetch all posts with pagination and sorting
        return postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection); // Placeholder return statement
    }
}
