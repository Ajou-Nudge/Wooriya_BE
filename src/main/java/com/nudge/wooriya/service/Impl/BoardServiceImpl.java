package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dao.CompanyPostDAO;
import com.nudge.wooriya.data.dao.GroupPostDAO;
import com.nudge.wooriya.data.dto.CompanyPostDto;
import com.nudge.wooriya.data.dto.GroupPostDto;
import com.nudge.wooriya.data.entity.CompanyPost;
import com.nudge.wooriya.data.entity.GroupPost;
import com.nudge.wooriya.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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
    public CompanyPostDto getCompanyPost(Long id) {
        CompanyPost companyPost = companyPostDAO.selectPost(id);
        CompanyPostDto companyPostDto = new CompanyPostDto();
        companyPostDto.setId(companyPost.getId());
        companyPostDto.setCompanyName(companyPost.getCompanyName());
        companyPostDto.setTitle(companyPost.getTitle());
        companyPostDto.setAuthorId(companyPost.getAuthorId());
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
            companyPostDto.setAuthorId(companyPost.getAuthorId());
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
        companyPost.setAuthorId(companyPostDto.getAuthorId());
        companyPost.setCompanyName(companyPostDto.getCompanyName());
        companyPost.setCoType(companyPostDto.getCoType());
        companyPost.setCoSize(companyPostDto.getCoSize());
        companyPost.setBody(companyPostDto.getBody());
        companyPost.setCreatedAt(LocalDateTime.now());
        companyPost.setUpdatedAt(LocalDateTime.now());
        Long postId = companyPostDAO.insertPost(companyPost);
        return postId;
    }

    public long deleteCompanyPost(Long id) {
        if(!SecurityUtil.getCurrentMemberId().getMemberId().equals(companyPostDAO.selectPost(id).getAuthorId())) {
            return -1L;
        }

        Long postId = companyPostDAO.deletePost(id);
        return postId;
    }

    @Override
    public Long updateCompanyPost(CompanyPostDto companyPostDto, Long id) {
        if(!SecurityUtil.getCurrentMemberId().getMemberId().equals(companyPostDto.getAuthorId())) {
            return -1L;
        }

        CompanyPost companyPost = new CompanyPost();
        companyPost.setId(id);
        companyPost.setTitle(companyPostDto.getTitle());
        companyPost.setAuthorId(companyPostDto.getAuthorId());
        companyPost.setCompanyName(companyPostDto.getCompanyName());
        companyPost.setCoType(companyPostDto.getCoType());
        companyPost.setCoSize(companyPostDto.getCoSize());
        companyPost.setBody(companyPostDto.getBody());
        companyPost.setCreatedAt(companyPostDAO.selectPost(id).getCreatedAt());
        companyPost.setUpdatedAt(LocalDateTime.now());

        Long postId = companyPostDAO.insertPost(companyPost);
        return postId;
    }

    @Override
    public GroupPostDto getGroupPost(Long id) {
        GroupPost groupPost = groupPostDAO.selectPost(id);
        GroupPostDto groupPostDto = new GroupPostDto();
        groupPostDto.setId(groupPost.getId());
        groupPostDto.setGroupName(groupPost.getGroupName());
        groupPostDto.setTitle(groupPost.getTitle());
        groupPostDto.setAuthorId(groupPost.getAuthorId());
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
            groupPostDto.setAuthorId(groupPost.getAuthorId());
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
        groupPost.setAuthorId(groupPostDto.getAuthorId());
        groupPost.setGroupName(groupPostDto.getGroupName());
        groupPost.setCoType(groupPostDto.getCoType());
        groupPost.setCoSize(groupPostDto.getCoSize());
        groupPost.setBody(groupPostDto.getBody());
        Long postId = groupPostDAO.insertPost(groupPost);
        return postId;
    }

    public Long deleteGroupPost(Long id) {
        if(!SecurityUtil.getCurrentMemberId().getMemberId().equals(companyPostDAO.selectPost(id).getAuthorId())) {
            return -1L;
        }

        Long postId = groupPostDAO.deletePost(id);
        return postId;
    }

    @Override
    public Long updateGroupPost(GroupPostDto groupPostDto, Long id) {
        if(!SecurityUtil.getCurrentMemberId().getMemberId().equals(groupPostDto.getAuthorId())) {
            return -1L;
        }

        GroupPost groupPost = new GroupPost();
        groupPost.setId(id);
        groupPost.setTitle(groupPostDto.getTitle());
        groupPost.setAuthorId(groupPostDto.getAuthorId());
        groupPost.setGroupName(groupPostDto.getGroupName());
        groupPost.setCoType(groupPostDto.getCoType());
        groupPost.setCoSize(groupPostDto.getCoSize());
        groupPost.setBody(groupPostDto.getBody());
        groupPost.setCreatedAt(groupPostDAO.selectPost(id).getCreatedAt());
        groupPost.setUpdatedAt(LocalDateTime.now());

        Long postId = groupPostDAO.insertPost(groupPost);
        return postId;
    }
}