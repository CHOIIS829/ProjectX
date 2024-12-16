package com.insu.backend.member.service;

import com.insu.backend.global.exception.AlreadyExistMemberIdException;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.entity.MemberSkill;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.member.repository.MemberSkillRepository;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.member.request.JoinRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final MemberSkillRepository memberSkillRepository;
    private final PasswordEncoder encoder;

    public void join(JoinRequest request) {

        if (memberRepository.existsByMemberId(request.getMemberId())) {
            throw new AlreadyExistMemberIdException();
        }

        Member member = Member.builder()
                .memberId(request.getMemberId())
                .memberPwd(encoder.encode(request.getMemberPwd()))
                .memberName(request.getMemberName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .git(request.getGit())
                .role("ROLE_ADMIN")
                .build();

        memberRepository.save(member);

        if(request.getSkills() != null) {
            request.getSkills().forEach(skillName -> {

                Skill findSkill = skillRepository.findBySkillName(skillName); //

                MemberSkill memberSkill = MemberSkill.builder()
                        .member(member)
                        .skill(findSkill)
                        .build();

                memberSkillRepository.save(memberSkill);
            });
        }


    }

}
