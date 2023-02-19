package com.nudge.concent.service;

import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.data.dto.PostImgaeDto;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
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