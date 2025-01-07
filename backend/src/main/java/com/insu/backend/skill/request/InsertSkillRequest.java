package com.insu.backend.skill.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertSkillRequest {

     private List<String> skills;
}
