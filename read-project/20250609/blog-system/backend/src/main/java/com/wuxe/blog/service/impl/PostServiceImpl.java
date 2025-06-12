package com.wuxe.blog.service.impl;

import com.wuxe.blog.entity.Post;
import com.wuxe.blog.repository.PostRepository;
import com.wuxe.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public List<Post> getAllPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isPresent()) {
            Post p = postOpt.get();
            p.setTitle(post.getTitle());
            p.setContent(post.getContent());
            p.setSummary(post.getSummary());
            p.setStatus(post.getStatus());
            p.setTags(post.getTags());
            p.setCategory(post.getCategory());
            p.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(p);
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
} 