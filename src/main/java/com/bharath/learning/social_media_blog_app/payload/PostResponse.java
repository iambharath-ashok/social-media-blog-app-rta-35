package com.bharath.learning.social_media_blog_app.payload;

import com.bharath.learning.social_media_blog_app.dto.PostDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(description = "Response payload for paginated list of posts")
public class PostResponse {

    // This class represents pagination and sorting information for a list of posts.

    //List of Posts Dto
    // page number
    // page size
    // total elements
    // total pages
    // is it last page

    @Schema(description = "List of posts in the current page", example = "[{\"id\":1,\"title\":\"My First Post\",\"description\":\"This is a brief description of my first post\",\"content\":\"This is the content of my first post. It contains detailed information about the topic.\"}]")
    private List<PostDto> posts;
    @Schema(description = "Current page number", example = "0")
    private int pageNumber;
    @Schema(description = "Size of the page (number of posts per page)", example = "10")
    private int pageSize;
    @Schema(description = "Total number of elements (posts) available", example = "100")
    private long totalElements;
    @Schema(description = "Total number of pages available", example = "10")
    private int totalPages;
    @Schema(description = "Indicates if the current page is the last page", example = "false")
    private boolean lastPage;
}
