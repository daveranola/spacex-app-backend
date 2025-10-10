package com.example.spacex_backend.controller;

import com.example.spacex_backend.model.User;
import com.example.spacex_backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository users, PasswordEncoder encoder) {
        this.users = users; this.encoder = encoder;
    }

    // POST /auth/login  { "email": "...", "password": "..." }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req, HttpSession session) {
        if ((req.email() == null || req.email().isBlank()) &&
                (req.username() == null || req.username().isBlank())) {
            return ResponseEntity.badRequest().body("Email and username required");
        }

        Optional<User> opt = (req.email() != null && !req.email().isBlank())
                ? users.findByEmail(req.email().trim().toLowerCase())
                : users.findByName(req.username().trim());

        if (opt.isEmpty() || !encoder.matches(req.password(), opt.get().getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        var user = opt.get();
        session.setAttribute("uid", user.getId());
        session.setAttribute("name", user.getName());
        session.setMaxInactiveInterval(60 * 60);
        return ResponseEntity.ok(new MeRes(user.getId(), user.getName()));
    }

    // GET /auth/me  -> who am I?
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        String name = (String) session.getAttribute("name");
        if (uid == null) return ResponseEntity.status(401).body("Not logged in");
        return ResponseEntity.ok(new MeRes(uid, name));
    }

    // POST /auth/logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    public record LoginReq(String username, String email, String password) {}
    public record MeRes(Integer userId, String name) {}
}

