package com.bharath.learning.social_media_blog_app.utiils.security;

import com.bharath.learning.social_media_blog_app.exceptions.BlogAPIException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtility {

    private Key jwtSecrete = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Initialize with your secret key

    private long jwtExpirationInMs = 1000 * 60 * 5; // 5 minute

    //Generate JWT token
    public String generateJwtToken(Authentication authentication) {
        // Use the authentication object to get user details and generate a JWT token
        // This typically involves creating a JWT with claims such as username, roles, etc.
        // Placeholder for actual JWT generation logic

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(jwtSecrete);

        String jwtToken = jwtBuilder.compact();

        return jwtToken;
    }

    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecrete).build().parse(token);
            return true; // Token is valid
        } catch (Exception e) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token"); // Token is invalid
        }
    }


    //Parse JWT token to extract user details
    public String getUsernameFromJwtToken(String token) {
      Claims claims = Jwts.parser()
                .setSigningKey(jwtSecrete)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username; // Extract the username from the token
    }
}
