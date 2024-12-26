package com.example.backend.Service;

import com.example.backend.DTO.PostDTO;
import com.example.backend.Repository.PostRepo;
import com.example.backend.Entity.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public List<PostDTO> getAllPosts() {
        List<Posts> posts = postRepo.findAll(); // Retrieves all posts from the database
        return posts.stream()
                .map(post -> PostDTO.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .imageName(post.getImageName())
                        .createdDate(post.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
    public Optional<PostDTO> getPostById(Integer postId) {
        Optional<Posts> post = postRepo.findById(postId); // Retrieve a single post by its ID
        return post.map(p -> PostDTO.builder()
                .postId(p.getPostId())
                .title(p.getTitle())
                .content(p.getContent())
                .imageName(p.getImageName())
                .createdDate(p.getCreatedDate())
                .build());
    }

}
