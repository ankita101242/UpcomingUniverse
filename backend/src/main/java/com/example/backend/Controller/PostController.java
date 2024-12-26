package com.example.backend.Controller;

import com.example.backend.DTO.PostDTO;
import com.example.backend.Repository.UserRepo;
import com.example.backend.Service.JwtService;
import com.example.backend.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") Integer postId, HttpServletRequest request) {
        // Extract JWT token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String jwtToken = authorizationHeader.substring(7); // Remove "Bearer " prefix
        try {
            String userEmail = jwtService.extractUsername(jwtToken);
            Integer extractedUserId = userRepo.findUserIdByEmail(userEmail);

            if (extractedUserId == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
            PostDTO post = postService.getPostById(postId).orElse(null);
            if (post != null && post.getUser().getUserId().equals(extractedUserId)) {
                return new ResponseEntity<>(post, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
