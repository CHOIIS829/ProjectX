package com.insu.backend.post.service;

import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.post.entity.Post;
import com.insu.backend.post.repository.PostRepository;
import com.insu.backend.post.request.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public void getPost(Long postNo) {


    }
}
