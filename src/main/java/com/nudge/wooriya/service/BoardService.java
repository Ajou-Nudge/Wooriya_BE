package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.CompanyPostDto;
import com.nudge.wooriya.data.dto.GroupPostDto;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    CompanyPostDto getCompanyPost(Long id);

    List<CompanyPostDto> getAllCompanyPost() throws UnsupportedEncodingException;

    Long saveCompanyPost(CompanyPostDto companyPostDto) throws SQLException;

    GroupPostDto getGroupPost(Long id);

    List<GroupPostDto> getAllGroupPost();

    Long saveGroupPost(GroupPostDto groupPostDto) throws SQLException, NoSuchAlgorithmException;
}