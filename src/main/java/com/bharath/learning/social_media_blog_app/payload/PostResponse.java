package com.bharath.learning.social_media_blog_app.payload;

import com.bharath.learning.social_media_blog_app.dto.PostDto;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostResponse {

    // This class represents pagination and sorting information for a list of posts.

    //List of Posts Dto
    // page number
    // page size
    // total elements
    // total pages
    // is it last page

    private List<PostDto> posts;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
