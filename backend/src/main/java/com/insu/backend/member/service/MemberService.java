package com.insu.backend.member.service;

import com.insu.backend.global.exception.AlreadyExistMemberIdException;
import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.global.mail.service.MailService;
import com.insu.backend.global.util.RandomString8;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.entity.MemberSkill;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.member.repository.MemberSkillRepository;
import com.insu.backend.member.request.FIndPwRequest;
import com.insu.backend.member.request.FindIdRequest;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.member.request.JoinRequest;
import jakarta.mail.MessagingException;
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
    private final MailService mailService;
    private final PasswordEncoder encoder;

    public void join(JoinRequest request) {

//        if (memberRepository.existsByMemberId(request.getMemberId())) {
//            throw new AlreadyExistMemberIdException();
//        }

        Member member = Member.builder()
                .memberId(request.getMemberId())
                .memberPwd(encoder.encode(request.getMemberPwd()))
                .memberName(request.getMemberName())
                .email(request.getEmail())
//                .phone(request.getPhone())
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

    public void checkId(String memberId) {
        if(memberRepository.existsByMemberId(memberId)) {
            throw new AlreadyExistMemberIdException();
        }
    }

    public String findId(FindIdRequest request) {
        String memberName = request.getMemberName();
        String email = request.getEmail();

        Member member = memberRepository.findByMemberNameAndEmail(memberName, email);

        return member.getMemberId();
    }

    public void findPw(FIndPwRequest request) throws MessagingException {
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(NotFoundMemberId::new);

        // 비밀번호 변경
        String newPwd = RandomString8.randomString(10);
        member.changePassword(encoder.encode(newPwd));

        memberRepository.save(member);

        // 메일 전송
        mailService.sendNewPasswordMail(member.getEmail(), newPwd);


    }
}
