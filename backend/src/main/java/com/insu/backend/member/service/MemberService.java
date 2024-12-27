package com.insu.backend.member.service;

import com.insu.backend.global.exception.*;
import com.insu.backend.global.mail.service.MailService;
import com.insu.backend.global.util.RandomString8;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.member.request.FIndPwRequest;
import com.insu.backend.member.request.FindIdRequest;
import com.insu.backend.member.request.InsertProfile;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.member.request.JoinRequest;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final MailService mailService;
    private final PasswordEncoder encoder;

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.max-size}")
    private long maxSize;

    public void join(JoinRequest request) {

        boolean existsByMemberId = memberRepository.existsByMemberId(request.getMemberId());
        if(existsByMemberId) {
            throw new AlreadyExistMemberIdException();
        }

        List<Skill> skillS = skillRepository.findBySkillNameIn(request.getSkills());

        Member member = Member.builder()
                .memberId(request.getMemberId())
                .memberPwd(encoder.encode(request.getMemberPwd()))
                .memberName(request.getMemberName())
                .email(request.getEmail())
                .git(request.getGit())
                .role("ROLE_ADMIN")
                .isProfileComplete("N")
                .skills(skillS)
                .build();

        memberRepository.save(member);
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

    @Transactional
    public String insertProfile(InsertProfile request) {
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(NotFoundMemberId::new);

        MultipartFile file = request.getFile();

        try{
            if(file.isEmpty()){
                throw new EmptyFIle();
            } else if (!file.getContentType().startsWith("image")) {
                throw new NotImageFile();
            } else if (file.getSize() > maxSize) { // 20MB
                throw new MaxFileSizeLimitException();
            }

            String profileImg = member.getProfileImg(); // "src/main/resources/upload/profile/~~~.jpg"

            if(profileImg != null) {
                Path deletePath = Paths.get(uploadPath, profileImg.replace("src/main/resources/upload/profile", ""));
                if(Files.exists(deletePath)) {
                    Files.delete(deletePath);
                }
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 원본 파일명
            Path filePath = Paths.get(uploadPath, fileName); // 저장할 경로

            if(!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String url = "src/main/resources/upload/profile/" + fileName;

            member.changeProfileImg(url);

            return url;
        } catch (IOException e) {
            throw new FailUploadFile();
        }

    }

    public String isProfileComplete(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(NotFoundMemberId::new);

        return member.getIsProfileComplete();
    }
}
