package com.example.backend.Repository;

import com.example.backend.Entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Posts, Integer> {
    List<Posts> findAll();
}
