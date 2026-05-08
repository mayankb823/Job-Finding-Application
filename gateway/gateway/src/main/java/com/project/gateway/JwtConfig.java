package com.project.gateway;


import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345iuye32uehbwd87ey17189w9";

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
        return NimbusReactiveJwtDecoder.withSecretKey(key).build();
    }
}