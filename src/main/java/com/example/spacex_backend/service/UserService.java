package com.example.spacex_backend.service;

import com.example.spacex_backend.exception.EmailAlreadyUsedException;
import com.example.spacex_backend.model.User;
import com.example.spacex_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//hash the password
@Service //creates an instance of it (spring does it) so we don't have to make one (a bean)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //spring automatically finds these beans and injects them here
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {

        if (user.getEmail() != null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (user.getPassword() != null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        String normEmail = user.getEmail().trim().toLowerCase();
        user.setEmail(normEmail);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyUsedException("Email already in use: " + user.getEmail());
        }

        //PasswordEncoder - interface with encode method
        String hashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashed);

        userRepository.save(user);
    }
}
