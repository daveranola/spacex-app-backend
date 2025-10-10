package com.example.spacex_backend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //holds beans
public class SecurityBeans {
    @Bean //declares third party class as bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //hashes password
    }
}
