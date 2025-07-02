package com.bharath.learning.social_media_blog_app;


import com.bharath.learning.social_media_blog_app.controller.PostController;
import com.bharath.learning.social_media_blog_app.dto.PostDto;
import com.bharath.learning.social_media_blog_app.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @InjectMocks
    public PostController postController;

    @Mock
    public PostService postService;

    @Test
    public void testCreatePost() {
        // Code to test the creation of a post
        List<PostDto> postDtoList = postController.getAllPosts();
        // Assertions can be added here to verify the behavior
        // For example, you can assert that the list is not null or empty
        assertNotNull(postDtoList);
     //   assertNotEquals(0,postDtoList.size());
    }
}
