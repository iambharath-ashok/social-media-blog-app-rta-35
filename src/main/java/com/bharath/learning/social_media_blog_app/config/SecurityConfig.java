package com.bharath.learning.social_media_blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {
        UserDetails bharath = User.builder()
                .username("bharath")
                .password(passwordEncoder.encode("bharath123"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder().username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(bharath, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Additional security configurations can be added here, such as HTTP security, CORS, CSRF, etc.
    // For example, you can configure HTTP security to allow specific endpoints, disable CSRF, etc.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())// Disabling CSRF for simplicity, not recommended for production
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/posts/**").hasRole("ADMIN") //   POST, PUT, DELETE
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow Swagger UI and API docs
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
