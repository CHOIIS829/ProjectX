package com.insu.backend.post.service;

import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.global.exception.InvalidPostAuthorException;
import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.global.exception.NotFoundPost;
import com.insu.backend.global.response.PageResponse;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.post.entity.Post;
import com.insu.backend.post.repository.PostRepository;
import com.insu.backend.post.request.CreatePostRequest;
import com.insu.backend.post.response.PostListResponse;
import com.insu.backend.post.response.PostOneResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void createPost(CreatePostRequest request, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(NotFoundMemberId::new);

        Post post = Post.builder()
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .category(request.getCategory())
                .isDeleted("N")
                .member(member)
                .build();

        member.addPost(post);

        postRepository.save(post);
    }

    public Optional<PostOneResponse> getPost(Long postNo) {
        Post post = postRepository.findById(postNo)
                .orElseThrow(NotFoundPost::new);

        if(post.getIsDeleted().equals("Y")) {
            return Optional.of(PostOneResponse.builder()
                    .postTitle("삭제된 프로젝트입니다.")
                    .build());
        }

        return Optional.of(PostOneResponse.builder()
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .category(post.getCategory())
                .author(post.getMember().getMemberId())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build());
    }

    public PageResponse<PostListResponse> getList(PageSearchDto postSearch) {
        return postRepository.getList(postSearch);
    }

    public PostOneResponse editPost(Long postNo, CreatePostRequest request, String memberId) {
        Post post = postRepository.findById(postNo)
                .orElseThrow(NotFoundPost::new);

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(NotFoundMemberId::new);

        if(!Objects.equals(post.getMember().getMemberId(), memberId)) {
            throw new InvalidPostAuthorException();
        }

        post.editPost(request.getPostTitle(), request.getPostContent(), request.getCategory());

        return PostOneResponse.builder()
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .category(post.getCategory())
                .author(post.getMember().getMemberId())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build();
    }

    @Transactional
    public void deletePost(Long postNo) {
        Post post = postRepository.findById(postNo)
                .orElseThrow(NotFoundPost::new);

        post.deletePost();
    }
}
