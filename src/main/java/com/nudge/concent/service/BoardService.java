package com.nudge.concent.service;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.data.dto.PostImgaeDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    CompanyPostDto getCompanyPost(Long id);

    List<CompanyPostDto> getAllCompanyPost() throws UnsupportedEncodingException;

    Long saveCompanyPost(CompanyPostDto companyPostDto) throws SQLException;

    String saveImage(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException;

    String getImage(String address) throws SQLException;

    GroupPostDto getGroupPost(Long id);

    List<GroupPostDto> getAllGroupPost();

    Long saveGroupPost(GroupPostDto groupPostDto) throws SQLException, NoSuchAlgorithmException;
}