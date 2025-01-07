package com.insu.backend.post.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<SuccessResponse<Void>> createPost() {

        return null;
    }

    @GetMapping("/get/{postNo}")
    public ResponseEntity<SuccessResponse<Void>> getPost() {

        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<Void>> getPostList() {

        return null;
    }

    @PatchMapping("/edit/{postNo}")
    public ResponseEntity<SuccessResponse<Void>> editPost() {

        return null;
    }

    @PostMapping("/delete/{postNo}")
    public ResponseEntity<SuccessResponse<Void>> deletePost() {

        return null;
    }
}
