package com.nudge.concent.service.Impl;

import com.nudge.concent.data.dao.CompanyPostDAO;
import com.nudge.concent.data.dao.GroupPostDAO;
import com.nudge.concent.data.dto.CompanyPostDto;
import com.nudge.concent.data.dto.GroupPostDto;
import com.nudge.concent.data.entity.CompanyPost;
import com.nudge.concent.data.entity.GroupPost;
import com.nudge.concent.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialBlob;
import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final CompanyPostDAO companyPostDAO;
    private final GroupPostDAO groupPostDAO;

    @Autowired
    public BoardServiceImpl(CompanyPostDAO companyPostDAO, GroupPostDAO groupPostDAO) {
        this.companyPostDAO = companyPostDAO;
        this.groupPostDAO = groupPostDAO;
    }

    @Override
    public CompanyPostDto getCompanyPost() {
        return null;
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

            byte[] byteImg = blobToBytes(companyPost.getImg());
            String base64Encode = byteToBase64(byteImg);
            base64Encode = "data:image/png;base64," + base64Encode;

            companyPostDto.setImg(base64Encode);
            companyPostDtos.add(companyPostDto);
        }
        return companyPostDtos;
    }

    @Override
    public Long saveCompanyPost(MultipartHttpServletRequest req) throws SQLException {
        byte imageArray [] = null;
        final String BASE_64_PREFIX = "data:image/png;base64,";
        try {
            String base64Url = String.valueOf(req.getParameter("img"));
            if (base64Url.startsWith(BASE_64_PREFIX)){
                imageArray =  Base64.getDecoder().decode(base64Url.substring(BASE_64_PREFIX.length()));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Blob imageBlob = new SerialBlob(imageArray);
        CompanyPost companyPost = new CompanyPost();
        companyPost.setTitle(req.getParameter("title"));
        companyPost.setCompanyName(req.getParameter("companyName"));
        companyPost.setCoType(req.getParameter("coType"));
        companyPost.setCoSize(Integer.parseInt(req.getParameter("coSize")));
        companyPost.setImg(imageBlob);
        Long postId = companyPostDAO.insertPost(companyPost);
        return postId;
    }

    @Override
    public GroupPostDto getGroupPost() {
        return null;
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

        Long postId = groupPostDAO.insertPost(groupPost);
        return postId;
    }

    // [blob 데이터를 바이트로 변환해주는 메소드]
    private static byte[] blobToBytes(Blob blob) {
        BufferedInputStream is = null;
        byte[] bytes = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;

            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    // [byte를 base64로 인코딩 해주는 메소드]
    private static String byteToBase64(byte[] arr) {
        String result = "";
        try {
            result = Base64Utils.encodeToString(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}