package com.insu.backend.post.controller;

import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.post.request.CreatePostRequest;
import com.insu.backend.post.response.PostListResponse;
import com.insu.backend.post.response.PostOneResponse;
import com.insu.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<SuccessResponse<Void>> createPost(@RequestBody CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();

        postService.createPost(request, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("게시글 작성 성공")
                .build());
    }

    @GetMapping("/get/{postNo}")
    public ResponseEntity<SuccessResponse<Optional<PostOneResponse>>> getPost(@PathVariable Long postNo) {
        Optional<PostOneResponse> post = postService.getPost(postNo);

        return ResponseEntity.ok(SuccessResponse.<Optional<PostOneResponse>>builder()
                .code("200")
                .message("게시글 조회 성공")
                .data(post)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<PageResponse<PostListResponse>>> getPostList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "all") String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author) {

        PageSearchDto postSearch = PageSearchDto.builder()
                .page(page)
                .size(size)
                .category(category)
                .keyword(keyword)
                .author(author)
                .build();

        PageResponse<PostListResponse> posts = postService.getList(postSearch);

        return ResponseEntity.ok(SuccessResponse.<PageResponse<PostListResponse>>builder()
                .code("200")
                .message("게시글 목록 조회 성공")
                .data(posts)
                .build());
    }

    @PatchMapping("/edit/{postNo}")
    public ResponseEntity<SuccessResponse<PostOneResponse>> editPost(@PathVariable Long postNo, @RequestBody CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();

        PostOneResponse postOne = postService.editPost(postNo, request, memberId);

        return ResponseEntity.ok(SuccessResponse.<PostOneResponse>builder()
                .code("200")
                .message("게시글 수정 성공")
                .data(postOne)
                .build());
    }

    @PostMapping("/delete/{postNo}")
    public ResponseEntity<SuccessResponse<Void>> deletePost(@PathVariable Long postNo) {
        postService.deletePost(postNo);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("게시글 삭제 성공")
                .build());
    }
}
