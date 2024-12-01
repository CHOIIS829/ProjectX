package com.insu.backend.member.service;

import com.insu.backend.global.exception.AlreadyExistMemberIdException;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.member.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public void join(JoinRequest request) {

        if(memberRepository.existsByMemberId(request.getMemberId())) {
            throw new AlreadyExistMemberIdException();
        }

        Member member = Member.builder()
                .memberId(request.getMemberId())
                .memberPwd(encoder.encode(request.getMemberPwd()))
                .memberName(request.getMemberName())
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);
    }

}
