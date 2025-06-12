package com.wuxe.blog.service;

import com.wuxe.blog.entity.User;
import java.util.Optional;

public interface UserService {
    User register(User user);
    String login(String usernameOrEmail, String password);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User updateUser(Long id, User user);
} 