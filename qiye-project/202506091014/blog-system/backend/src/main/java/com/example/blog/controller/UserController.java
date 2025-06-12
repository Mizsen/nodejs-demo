package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    // @Value("${jwt.secret}")
    // private String SECRET_KEY;

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.info("Register request: {}", user);
        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warn("Register failed: username {} already taken", user.getUsername());
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("Register failed: email {} already in use", user.getEmail());
            return ResponseEntity.badRequest().body("Email is already in use");
        }
        // Password encryption and other validations should be added here
        user.setRegisterTime(java.time.LocalDateTime.now());
        userRepository.save(user);
        logger.info("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        logger.info("Login request: {}", user.getUsername());
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isEmpty()) {
            logger.warn("Login failed: account {} does not exist", user.getUsername());
            return ResponseEntity.badRequest().body("Account does not exist");
        }
        // Password check and JWT token generation should be added here
        if (!existingUser.get().getPassword().equals(user.getPassword())) {
            logger.warn("Login failed: incorrect password for user {}", user.getUsername());
            return ResponseEntity.badRequest().body("Incorrect password");
        }
      // 生成 token 和过期时间
        String token = UUID.randomUUID().toString() + "-" + user.getUsername();
        LocalDateTime expiration = LocalDateTime.now().plusHours(24); // 例如，设置 token 有效期为 24 小时
        existingUser.get().setToken(token);
        existingUser.get().setTokenExpiration(expiration);
        userRepository.save(existingUser.get());
        logger.info("Login successful: {}", user.getUsername());
        return ResponseEntity.ok(Map.of("token", token, "msg", user.toString()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        logger.info("Get user info request: id={}", id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.info("User info found: {}", user.get().getUsername());
            return ResponseEntity.ok(user.get());
        } else {
            logger.warn("User info not found: id={}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @RequestBody User updatedUser) {
        logger.info("Update user info request: id={}, data={}", id, updatedUser);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            logger.warn("Update failed: user id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        user.setNickname(updatedUser.getNickname());
        user.setAvatarUrl(updatedUser.getAvatarUrl());
        user.setEmail(updatedUser.getEmail());
        userRepository.save(user);
        logger.info("User info updated: id={}", id);
        return ResponseEntity.ok("User info updated");
    }

    // 获取当前用户信息：校验 token 和过期时间
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserInfo(@RequestHeader("Authorization") String authorization) {
        logger.info("Get current user info request");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            logger.warn("Authorization header is missing or invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        String token = authorization.substring(7);
        Optional<User> user = userRepository.findByToken(token);
        if (user.isPresent()) {
             LocalDateTime now = LocalDateTime.now();
            if (user.get().getTokenExpiration() == null || user.get().getTokenExpiration().isBefore(now)) {
                // 如果 token 已过期，清空 token
                user.get().setToken(null);
                user.get().setTokenExpiration(null);
                userRepository.save(user.get());
                logger.warn("Token has expired for user: {}", user.get().getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired. Please login again.");
            }

            logger.info("Current user info found: {}", user.get().getUsername());
           
            return ResponseEntity.ok(user.get());
        } else {
            logger.warn("Current user info not found for token: {}", token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
