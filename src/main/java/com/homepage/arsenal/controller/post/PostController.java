package com.homepage.arsenal.controller.post;

import com.homepage.arsenal.dto.PostForm;
import com.homepage.arsenal.dto.PostUpdateForm;
import com.homepage.arsenal.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/newPost")
    public ResponseEntity<String> getPost(@RequestBody PostForm postForm) {
        postService.savePost(postForm);
        return ResponseEntity.ok("게시물 저장 성공");
    }

    @PostMapping("/post")
    public ResponseEntity<?> updatePost(@RequestBody PostUpdateForm postForm) {
        postService.updatePost(postForm.getId(),postForm.getTitle(), postForm.getContent());
        return ResponseEntity.ok("게시물 업데이트 성공");
    }
}
