package com.nudge.concent.service;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardService {

    CompanyPostDto getCompanyPost();

    List<CompanyPostDto> getAllCompanyPost();

    Long saveCompanyPost(CompanyPostDto companyPostDto);

    GroupPostDto getGroupPost();

    List<GroupPostDto> getAllGroupPost();

    Long saveGroupPost(GroupPostDto groupPostDto);
}