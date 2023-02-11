package com.nudge.concent.service;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    CompanyPostDto getCompanyPost(Long id);

    List<CompanyPostDto> getAllCompanyPost() throws UnsupportedEncodingException;

    Long saveCompanyPost(MultipartHttpServletRequest req) throws SQLException;

    String saveImage(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException;

    GroupPostDto getGroupPost(Long id);

    List<GroupPostDto> getAllGroupPost();

    Long saveGroupPost(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException;
}