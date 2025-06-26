package com.bharath.learning.social_media_blog_app.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
