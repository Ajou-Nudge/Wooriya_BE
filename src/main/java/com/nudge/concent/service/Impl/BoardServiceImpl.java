package com.nudge.concent.service.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.dao.GroupPostDAO;
import com.nudge.concent.data.dao.PostDAO;
import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.GroupPost;
import com.nudge.concent.data.entity.PostImage;
import com.nudge.concent.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final CompanyPostDAO companyPostDAO;
    private final GroupPostDAO groupPostDAO;
    private final PostDAO postDAO;

    @Autowired
    public BoardServiceImpl(CompanyPostDAO companyPostDAO, GroupPostDAO groupPostDAO, PostDAO postDAO) {
        this.companyPostDAO = companyPostDAO;
        this.groupPostDAO = groupPostDAO;
        this.postDAO = postDAO;
    }

    @Override
    public CompanyPostDto getCompanyPost(Long id) {
        CompanyPost companyPost = companyPostDAO.selectPost(id);
        CompanyPostDto companyPostDto = new CompanyPostDto();
        companyPostDto.setId(companyPost.getId());
        companyPostDto.setCompanyName(companyPost.getCompanyName());
        companyPostDto.setTitle(companyPost.getTitle());
        companyPostDto.setCoType(companyPost.getCoType());
        companyPostDto.setCoSize(companyPost.getCoSize());
        companyPostDto.setBody(companyPost.getBody());

        return companyPostDto;
    }

    @Override
    public List<CompanyPostDto> getAllCompanyPost() {
        List<CompanyPost> companyPosts = companyPostDAO.selectAllPost();
        List<CompanyPostDto> companyPostDtos = new ArrayList<>();
        for (CompanyPost companyPost : companyPosts) {
            CompanyPostDto companyPostDto = new CompanyPostDto();
            companyPostDto.setId(companyPost.getId());
            companyPostDto.setCompanyName(companyPost.getCompanyName());
            companyPostDto.setTitle(companyPost.getTitle());
            companyPostDto.setCoType(companyPost.getCoType());
            companyPostDto.setCoSize(companyPost.getCoSize());

            companyPostDtos.add(companyPostDto);
        }
        return companyPostDtos;
    }

    @Override
    public Long saveCompanyPost(CompanyPostDto companyPostDto) {
        CompanyPost companyPost = new CompanyPost();
        companyPost.setTitle(companyPostDto.getTitle());
        companyPost.setCompanyName(companyPostDto.getCompanyName());
        companyPost.setCoType(companyPostDto.getCoType());
        companyPost.setCoSize(companyPostDto.getCoSize());
        companyPost.setBody(companyPostDto.getBody());
        Long postId = companyPostDAO.insertPost(companyPost);
        return postId;
    }

    @Override
    public String saveImage(MultipartHttpServletRequest req) throws SQLException, NoSuchAlgorithmException {
        byte[] imageArray = null;
        final String BASE_64_PREFIX = "data:image/png;base64,";
        String addrData = "";
        try {
            String base64Url = String.valueOf(req.getParameter("img"));
            if (base64Url.startsWith(BASE_64_PREFIX)){
                imageArray =  Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX.length()));
                addrData = base64Url.substring(BASE_64_PREFIX.length());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Blob imageBlob = new SerialBlob(imageArray);
        byte[] addr = sha256(addrData.substring(6, 16));

        PostImage postImage = new PostImage();
        postImage.setImg(imageBlob);
        postImage.setAddress(addr.toString());
        String imgAddr = postDAO.insertImage(postImage);
        return imgAddr;
    }

    @Override
    public GroupPostDto getGroupPost(Long id) {
        GroupPost groupPost = groupPostDAO.selectPost(id);
        GroupPostDto groupPostDto = new GroupPostDto();
        groupPostDto.setId(groupPost.getId());
        groupPostDto.setGroupName(groupPost.getGroupName());
        groupPostDto.setTitle(groupPost.getTitle());
        groupPostDto.setCoType(groupPost.getCoType());
        groupPostDto.setCoSize(groupPost.getCoSize());
        groupPostDto.setBody(groupPost.getBody());

        return groupPostDto;
    }

    @Override
    public List<GroupPostDto> getAllGroupPost() {
        List<GroupPost> groupPosts = groupPostDAO.selectAllPost();
        List<GroupPostDto> groupPostDtos = new ArrayList<>();
        for (GroupPost groupPost : groupPosts) {
            GroupPostDto groupPostDto = new GroupPostDto();
            groupPostDto.setId(groupPost.getId());
            groupPostDto.setGroupName(groupPost.getGroupName());
            groupPostDto.setTitle(groupPost.getTitle());
            groupPostDto.setCoType(groupPost.getCoType());
            groupPostDto.setCoSize(groupPost.getCoSize());

            groupPostDtos.add(groupPostDto);
        }
        return groupPostDtos;
    }

    @Override
    public Long saveGroupPost(GroupPostDto groupPostDto) {
        GroupPost groupPost = new GroupPost();
        groupPost.setTitle(groupPostDto.getTitle());
        groupPost.setGroupName(groupPostDto.getGroupName());
        groupPost.setCoType(groupPostDto.getCoType());
        groupPost.setCoSize(groupPostDto.getCoSize());
        groupPost.setBody(groupPostDto.getBody());
        Long postId = groupPostDAO.insertPost(groupPost);
        return postId;
    }

    private static byte[] sha256(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data.getBytes());
        return messageDigest.digest();
    }
}