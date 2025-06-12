package com.example.blog.controller;

import com.example.blog.entity.Like;
import com.example.blog.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping
    public ResponseEntity<?> addLike(@RequestBody Like like) {
        // Ideally check if user already liked the post, to be implemented
        likeRepository.save(like);
        return ResponseEntity.ok("Like added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeLike(@PathVariable Long id) {
        Optional<Like> like = likeRepository.findById(id);
        if (like.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        likeRepository.deleteById(id);
        return ResponseEntity.ok("Like removed successfully");
    }
}
