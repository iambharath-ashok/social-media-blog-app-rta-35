package com.bharath.learning.social_media_blog_app.exceptions;

public class CommentNotFoundException extends  RuntimeException{
    public CommentNotFoundException(String message) {
        super(message);
    }

    public CommentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
