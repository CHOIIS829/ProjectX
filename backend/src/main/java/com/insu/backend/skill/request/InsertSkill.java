package com.insu.backend.skill.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertSkill {

     private List<String> skills;
}
