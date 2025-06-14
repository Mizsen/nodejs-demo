package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        // 检查用户是否存在
        if (comment.getAuthor() == null || comment.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().body("评论作者信息不能为空");
        }
        Optional<User> userOptional = userRepository.findById(comment.getAuthor().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("用户不存在");
        }

        // 检查文章是否存在
        if (comment.getPost() == null || comment.getPost().getId() == null) {
            return ResponseEntity.badRequest().body("评论文章信息不能为空");
        }
        Optional<Post> postOptional = postRepository.findById(comment.getPost().getId());
        if (postOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("文章不存在");
        }

        // 设置评论信息
        comment.setAuthor(userOptional.get());
        comment.setPost(postOptional.get());
        comment.setCreateTime(java.time.LocalDateTime.now());
        
        commentRepository.save(comment);
        return ResponseEntity.ok("评论发布成功");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentRepository.findAll(); // Ideally filter by postId, to be implemented
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        if (!commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        commentRepository.deleteById(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
