package com.nudge.wooriya.data.dto.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class UpdateMemberProfileDto {
    private String introduction;
    private String profilePhoto;
    private String name;
    private Set<String> relatedLink;
}
