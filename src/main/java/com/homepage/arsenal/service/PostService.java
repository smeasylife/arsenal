package com.homepage.arsenal.service;

import com.homepage.arsenal.domain.Post;
import com.homepage.arsenal.dto.PostForm;
import com.homepage.arsenal.exception.PostException;
import com.homepage.arsenal.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post savePost(PostForm postForm) {
        return postRepository.save(Post.builder()
                .title(postForm.getTitle())
                .content(postForm.getContent())
                .build());
    }

    @Transactional
    public void updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostException("Post not found")
        );
        post.updatePost(title, content);
    }
}
