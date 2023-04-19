package com.example.bloglv2.post.repository;

import com.example.bloglv2.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
