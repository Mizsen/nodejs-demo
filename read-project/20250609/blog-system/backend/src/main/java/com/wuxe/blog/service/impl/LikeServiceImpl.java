package com.wuxe.blog.service.impl;

import com.wuxe.blog.entity.Like;
import com.wuxe.blog.repository.LikeRepository;
import com.wuxe.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public boolean likePost(Long userId, Long postId) {
        if (likeRepository.findByUserIdAndPostId(userId, postId).isPresent()) {
            return false;
        }
        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);
        like.setCreatedAt(LocalDateTime.now());
        likeRepository.save(like);
        return true;
    }

    @Override
    public boolean unlikePost(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId)
                .map(like -> {
                    likeRepository.delete(like);
                    return true;
                }).orElse(false);
    }

    @Override
    public Long countLikesByPostId(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    @Override
    public boolean hasUserLiked(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }
} 