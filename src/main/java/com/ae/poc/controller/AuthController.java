package com.ae.poc.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.poc.entity.LoginRequest;
import com.ae.poc.entity.User;
import com.ae.poc.repo.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // Add allowCredentials
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) { // Add HttpSession parameter
        try {
            System.out.println("Login attempt for: " + loginRequest.getUsername());
            
            Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
            
            if (userOptional.isEmpty()) {
                System.out.println("User not found: " + loginRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
            }

            User user = userOptional.get();
            
            // Check password (you should use password encoding in production)
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                System.out.println("Invalid password for user: " + loginRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
            }

            // Store user in session for session-based authentication
            session.setAttribute("authenticated", true);
            session.setAttribute("user", user);

            // Generate token (simplified - use JWT in production)
            String token = "generated-token-" + user.getId() + "-" + System.currentTimeMillis();
            
            // Return user info without password - match frontend expectation
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole()
            ));

            System.out.println("Login successful for: " + loginRequest.getUsername());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Login failed: " + e.getMessage()));
        }
    }
}