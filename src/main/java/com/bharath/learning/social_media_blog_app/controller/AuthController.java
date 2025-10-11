package com.bharath.learning.social_media_blog_app.controller;

import com.bharath.learning.social_media_blog_app.dto.AuthRequestDto;
import com.bharath.learning.social_media_blog_app.dto.AuthResponseDto;
import com.bharath.learning.social_media_blog_app.dto.RegisterDto;
import com.bharath.learning.social_media_blog_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //login or /singin
    @PostMapping(value = {"/signin", "/login"})
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
      //Get the JWT token from the service
        String token = authService.login(authRequestDto);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(token);

        return ResponseEntity.ok(authResponseDto);
    }

    //register or /signup
    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
