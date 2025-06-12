package com.wuxe.blog.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String username;

    @Column(nullable = false, unique = true, length = 64)
    private String email;

    @Column(nullable = false)
    private String password;

    private String avatar;

    private String nickname;

    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;
} 