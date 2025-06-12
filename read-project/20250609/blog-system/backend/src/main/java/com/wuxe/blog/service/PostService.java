package com.wuxe.blog.service;

import com.wuxe.blog.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    Optional<Post> getPostById(Long id);
    List<Post> getPostsByUserId(Long userId);
    List<Post> getAllPosts(int page, int size);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
} 