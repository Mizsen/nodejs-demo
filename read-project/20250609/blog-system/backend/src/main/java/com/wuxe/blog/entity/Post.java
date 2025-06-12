package com.wuxe.blog.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 128)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 256)
    private String summary;

    @Column(length = 16)
    private String status; // draft/published

    @Column(length = 128)
    private String tags; // JSON 字符串

    @Column(length = 64)
    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer views = 0;

    private Integer likesCount = 0;
} 