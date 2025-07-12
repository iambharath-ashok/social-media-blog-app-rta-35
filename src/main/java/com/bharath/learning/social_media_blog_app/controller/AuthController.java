package com.bharath.learning.social_media_blog_app.controller;

import com.bharath.learning.social_media_blog_app.dto.AuthRequestDto;
import com.bharath.learning.social_media_blog_app.dto.AuthResponseDto;
import com.bharath.learning.social_media_blog_app.dto.RegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    //login or /singin
    @PostMapping(value = {"/signin", "/login"})
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        // Logic to handle user authentication
        return ResponseEntity.ok().build();
    }

    //register or /signup
    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        // Logic to handle user registration
        return ResponseEntity.ok("User registered successfully");
    }

}
