package com.bharath.learning.social_media_blog_app.utiils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodingDecodingUtility {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Bharath:: "+passwordEncoder.encode("Guru0421"));
        System.out.println("Admin:: "+passwordEncoder.encode("admin"));
        System.out.println("Master:: "+passwordEncoder.encode("master"));

    }
}
