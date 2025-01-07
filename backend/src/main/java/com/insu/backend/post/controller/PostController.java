package com.insu.backend.post.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.post.request.CreatePostRequest;
import com.insu.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<SuccessResponse<Void>> createPost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreatePostRequest request) {
        String memberId = userDetails.getUsername();

        postService.createPost(request, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("게시글 작성 성공")
                .build());
    }

    @GetMapping("/get/{postNo}")
    public ResponseEntity<SuccessResponse<Void>> getPost(@PathVariable Long postNo) {
        postService.getPost(postNo);
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
