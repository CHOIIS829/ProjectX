package com.insu.backend.skill.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.skill.request.InsertSkill;
import com.insu.backend.skill.response.SkillNameResponse;
import com.insu.backend.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody InsertSkill skillList) {
        skillService.insert(skillList);
        return ResponseEntity.status(201).body("등록 성공");
    }

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<List<SkillNameResponse>>> getList(@RequestParam(required = false) String keyword) {
        List<SkillNameResponse> list = skillService.getList(keyword);

        return ResponseEntity.ok(SuccessResponse.<List<SkillNameResponse>>builder()
                .code("200")
                .message("스킬 리스트 조회 성공")
                .data(list)
                .build());
    }

}
