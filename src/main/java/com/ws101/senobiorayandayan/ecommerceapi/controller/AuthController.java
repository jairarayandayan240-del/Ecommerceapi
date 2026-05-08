 package com.ws101.senobiorayandayan.ecommerceapi.controller;

import com.ws101.senobiorayandayan.ecommerceapi.model.User;
import com.ws101.senobiorayandayan.ecommerceapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String role = request.getOrDefault("role", "USER");

        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        if (password == null || password.length() < 6) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password must be at least 6 characters"));
        }
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role.toUpperCase());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Not authenticated"));
        }
        return ResponseEntity.ok(Map.of(
            "username", user.getUsername(),
            "role", user.getRole()
        ));
    }
}