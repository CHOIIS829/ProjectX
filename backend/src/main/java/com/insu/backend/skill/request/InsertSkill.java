package com.insu.backend.skill.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertSkill {

     @NotBlank(message = "스킬을 입력해주세요.")
     private List<String> skills;
}
