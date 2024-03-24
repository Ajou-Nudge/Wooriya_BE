package com.nudge.wooriya.application.domain;

import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.in.Prorposal.dto.*;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Notification;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.ProposalPost;
import com.nudge.wooriya.adapter.out.persistence.Repo.NotificationRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.ProposalPostRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.ProposalRepository;
import com.nudge.wooriya.application.port.in.Mail.MailUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardUsecase implements com.nudge.wooriya.application.port.in.Prorposal.BoardUsecase {
    private final ProposalPostRepository proposalPostRepository;
    private final ProposalRepository proposalRepository;
    private final MailUsecase mailUsecase;
    private final NotificationRepository notificationRepository;

    @Autowired
    public BoardUsecase(ProposalPostRepository proposalPostRepository, ProposalRepository proposalRepository, MailUsecase mailUsecase, NotificationRepository notificationRepository) {
        this.proposalPostRepository = proposalPostRepository;
        this.proposalRepository = proposalRepository;
        this.mailUsecase = mailUsecase;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ProposalPostResponseDto getProposalPost(Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));
        ProposalPostResponseDto proposalPostResponseDto = new ProposalPostResponseDto();
        proposalPostResponseDto.setId(proposalPost.getId());
        proposalPostResponseDto.setTitle(proposalPost.getTitle());
        proposalPostResponseDto.setAuthor(proposalPost.getAuthor());
        proposalPostResponseDto.setAffiliateKinds(proposalPost.getAffiliateKinds());
        proposalPostResponseDto.setAffiliateForm(proposalPost.getAffiliateForm());
        proposalPostResponseDto.setAffiliatePeriod(proposalPost.getAffiliatePeriod());
        proposalPostResponseDto.setRecruitmentPeriod(proposalPost.getRecruitmentPeriod());
        proposalPostResponseDto.setPrLinks(proposalPost.getPrLinks());
        proposalPostResponseDto.setPhotos(proposalPost.getPhotos());
        proposalPostResponseDto.setDetail(proposalPost.getDetail());

        return proposalPostResponseDto;
    }

    @Override
    public List<ProposalPostResponseDto> getAllProposalPost() {
        List<ProposalPost> proposalPosts = proposalPostRepository.findAll();
        List<ProposalPostResponseDto> proposalPostResponseDtos = new ArrayList<>();
        for (ProposalPost proposalPost : proposalPosts) {
            ProposalPostResponseDto proposalPostResponseDto = new ProposalPostResponseDto();
            proposalPostResponseDto.setId(proposalPost.getId());
            proposalPostResponseDto.setTitle(proposalPost.getTitle());
            proposalPostResponseDto.setAuthor(proposalPost.getAuthor());
            proposalPostResponseDto.setAffiliateKinds(proposalPost.getAffiliateKinds());
            proposalPostResponseDto.setAffiliateForm(proposalPost.getAffiliateForm());
            proposalPostResponseDto.setAffiliatePeriod(proposalPost.getAffiliatePeriod());
            proposalPostResponseDto.setRecruitmentPeriod(proposalPost.getRecruitmentPeriod());
            proposalPostResponseDto.setPrLinks(proposalPost.getPrLinks());
            proposalPostResponseDto.setPhotos(proposalPost.getPhotos());
            proposalPostResponseDto.setDetail(proposalPost.getDetail());

            proposalPostResponseDtos.add(proposalPostResponseDto);
        }
        return proposalPostResponseDtos;
    }

    @Override
    public Long saveProposalPost(ProposalPostRequestDto proposalPostRequestDto) {
        ProposalPost proposalPost = new ProposalPost();
        proposalPost.setTitle(proposalPostRequestDto.getTitle());
        proposalPost.setAuthor(SecurityUtil.getCurrentMemberId().getEmail());
        proposalPost.setAffiliateKinds(proposalPostRequestDto.getAffiliateKinds());
        proposalPost.setAffiliateForm(proposalPostRequestDto.getAffiliateForm());
        proposalPost.setAffiliatePeriod(proposalPostRequestDto.getAffiliatePeriod());
        proposalPost.setRecruitmentPeriod(proposalPostRequestDto.getRecruitmentPeriod());
        proposalPost.setPrLinks(proposalPostRequestDto.getPrLinks());
        proposalPost.setPhotos(proposalPostRequestDto.getPhotos());
        proposalPost.setDetail(proposalPostRequestDto.getDetail());
        proposalPost.setCreatedAt(LocalDateTime.now());
        proposalPost.setUpdatedAt(LocalDateTime.now());
        Long id = proposalPostRepository.save(proposalPost).getId();

        return id;
    }

    public Long deleteProposalPost(Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));
        if(!SecurityUtil.getCurrentMemberId().getEmail().equals(proposalPost.getAuthor())) {
            return -1L;
        }
        proposalPostRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateProposalPost(ProposalPostRequestDto proposalPostRequestDto, Long id) throws Exception {
        ProposalPost proposalPost = proposalPostRepository.findById(id).orElseThrow(() -> new Exception("post not found"));

        if(!SecurityUtil.getCurrentMemberId().getEmail().equals(proposalPost.getAuthor())) {
            return -1L;
        }

        proposalPost.setTitle(proposalPostRequestDto.getTitle());
        proposalPost.setAffiliateKinds(proposalPostRequestDto.getAffiliateKinds());
        proposalPost.setAffiliateForm(proposalPostRequestDto.getAffiliateForm());
        proposalPost.setAffiliatePeriod(proposalPostRequestDto.getAffiliatePeriod());
        proposalPost.setRecruitmentPeriod(proposalPostRequestDto.getRecruitmentPeriod());
        proposalPost.setPrLinks(proposalPostRequestDto.getPrLinks());
        proposalPost.setPhotos(proposalPostRequestDto.getPhotos());
        proposalPost.setDetail(proposalPostRequestDto.getDetail());
        proposalPost.setUpdatedAt(LocalDateTime.now());
        Long postId = proposalPostRepository.save(proposalPost).getId();

        return postId;
    }

    @Override
    public Boolean sendProposal(Long postId, ProposalRequestDto proposalRequestDto) throws Exception {
        Proposal proposal = new Proposal();
        proposal.setCompanyEmail(SecurityUtil.getCurrentMemberId().getEmail());
        proposal.setMessage(proposalRequestDto.getMessage());
        proposal.setPostId(postId);

        String organizationEmail = proposalPostRepository.findById(postId).get().getAuthor();
        proposal.setOrganizationEmail(organizationEmail);
        proposal.setCreatedAt(LocalDateTime.now());
        proposal.setUpdatedAt(LocalDateTime.now());
        Long proposalId = proposalRepository.save(proposal).getId();

        ProposalPost proposalPost = proposalPostRepository.findById(postId)
                .orElseThrow(() -> new Exception("post not found"));

        Notification notification = new Notification();
        notification.setProposalId(proposalId);
        notification.setReceiver(proposalPost.getAuthor());
        notification.setMessage(proposalRequestDto.getMessage());
        notification.setIsRead(false);
        notificationRepository.save(notification);

        // ProposalRequestDto to MailRequestDto 만들기
        mailUsecase.sendProposalMail(postId, proposalRequestDto);
        return true;
    }
}