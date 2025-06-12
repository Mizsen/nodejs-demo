package com.wuxe.blog.service;

import com.wuxe.blog.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getCommentsByUserId(Long userId);
    void deleteComment(Long id);
    Optional<Comment> getCommentById(Long id);
} 