package com.bharath.learning.social_media_blog_app.service.impl;

import com.bharath.learning.social_media_blog_app.dto.AuthRequestDto;
import com.bharath.learning.social_media_blog_app.dto.RegisterDto;
import com.bharath.learning.social_media_blog_app.entity.Role;
import com.bharath.learning.social_media_blog_app.entity.User;
import com.bharath.learning.social_media_blog_app.exceptions.BlogAPIException;
import com.bharath.learning.social_media_blog_app.repository.RoleRepository;
import com.bharath.learning.social_media_blog_app.repository.UserRepository;
import com.bharath.learning.social_media_blog_app.service.AuthService;
import com.bharath.learning.social_media_blog_app.utiils.security.JwtTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(AuthRequestDto authRequestDto) {
        // Authenticate the user using Spring AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(),
                        authRequestDto.getPassword()
                )
        );

        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenUtility.generateJwtToken(authentication);
        return jwtToken;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // Check if username already exists in DB
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // check if email already exists in DB
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // Map RegisterDto to User entity
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));



        // Map roles to user entity
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new BlogAPIException(HttpStatus.NOT_FOUND, "Role not found"));
        roles.add(userRole);
        user.setRoles(roles);

        // Save user entity to DB
        userRepository.save(user);


        return "User registered successfully";
    }
}
