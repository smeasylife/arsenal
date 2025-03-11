package com.homepage.arsenal.repository;

import com.homepage.arsenal.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.ObjectPostProcessor;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
}
