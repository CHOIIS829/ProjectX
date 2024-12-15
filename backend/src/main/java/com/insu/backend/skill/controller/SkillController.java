package com.insu.backend.skill.controller;

import com.insu.backend.skill.request.InsertSkill;
import com.insu.backend.skill.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody InsertSkill skillList) {

        skillService.insert(skillList);

        return ResponseEntity.status(201).body("등록 성공");
    }

}
