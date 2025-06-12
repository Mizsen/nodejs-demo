package com.wuxe.blog.service;

public interface LikeService {
    boolean likePost(Long userId, Long postId);
    boolean unlikePost(Long userId, Long postId);
    Long countLikesByPostId(Long postId);
    boolean hasUserLiked(Long userId, Long postId);
} 