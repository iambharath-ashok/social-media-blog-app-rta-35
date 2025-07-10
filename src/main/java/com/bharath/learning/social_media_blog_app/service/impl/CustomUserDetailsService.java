package com.bharath.learning.social_media_blog_app.service.impl;

import com.bharath.learning.social_media_blog_app.entity.Role;
import com.bharath.learning.social_media_blog_app.entity.User;
import com.bharath.learning.social_media_blog_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // This method should be implemented to load user details from the database or any other source.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<Role> roles = user.getRoles();
        Set<GrantedAuthority> authorities  = roles.stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
