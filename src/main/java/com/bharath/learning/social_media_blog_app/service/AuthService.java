package com.bharath.learning.social_media_blog_app.service;

import com.bharath.learning.social_media_blog_app.dto.AuthRequestDto;
import com.bharath.learning.social_media_blog_app.dto.RegisterDto;

public interface AuthService {

    String login(AuthRequestDto authRequestDto);
    String register(RegisterDto registerDto);
}
