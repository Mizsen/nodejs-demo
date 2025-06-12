package com.wuxe.blog.controller;

import com.wuxe.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<?> likePost(@PathVariable Long userId, @PathVariable Long postId) {
        boolean liked = likeService.likePost(userId, postId);
        return liked ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("已点赞");
    }

    @DeleteMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<?> unlikePost(@PathVariable Long userId, @PathVariable Long postId) {
        boolean unliked = likeService.unlikePost(userId, postId);
        return unliked ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("未点赞");
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<Long> countLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.countLikesByPostId(postId));
    }
} 