package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.*;
import com.nudge.wooriya.data.repository.*;
import com.nudge.wooriya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final ProposalRepository proposalRepository;
    private final NotificationRepository notificationRepository;
    private final ProposalPostRepository proposalPostRepository;

    @Autowired
    public UserServiceImpl(CompanyRepository companyRepository, OrganizationRepository organizationRepository,
                           ProposalRepository proposalRepository,
                           NotificationRepository notificationRepository,
                           ProposalPostRepository proposalPostRepository) {
        this.companyRepository = companyRepository;
        this.organizationRepository = organizationRepository;
        this.proposalRepository = proposalRepository;
        this.notificationRepository = notificationRepository;
        this.proposalPostRepository = proposalPostRepository;
    }

    @Override
    public ProfileDto profile(String email) throws Exception {
        boolean isCompanyExist = companyRepository.existsByEmail(email);

        if (isCompanyExist) {
            Company company = companyRepository.findById(email).orElseThrow(() -> new Exception("company not found"));
            ProfileDto profileDto = new ProfileDto();
            profileDto.setEmail(email);
            profileDto.setIsCompany(true);

            CompanyProfileDto companyProfileDto = new CompanyProfileDto();
            companyProfileDto.setEmail(company.getEmail());
            companyProfileDto.setCompanyName(company.getCompanyName());
            companyProfileDto.setRepresentativeName(company.getRepresentativeName());
            companyProfileDto.setRepresentativeNum(company.getRepresentativeNum());
            companyProfileDto.setKind(company.getKind());
            companyProfileDto.setHistory(company.getHistory());
            companyProfileDto.setGreetings(company.getGreetings());

            profileDto.setCompanyProfileDto(companyProfileDto);

            return profileDto;
        } else {
            try {
                Organization organization = organizationRepository.findById(email).orElseThrow(() -> new Exception("organization not found"));
                ProfileDto profileDto = new ProfileDto();
                profileDto.setEmail(email);
                profileDto.setIsCompany(false);

                OrganizationProfileDto organizationProfileDto = new OrganizationProfileDto();
                organizationProfileDto.setEmail(organization.getEmail());
                organizationProfileDto.setOrganizationName(organization.getOrganizationName());
                organizationProfileDto.setRepresentativeName(organization.getRepresentativeName());
                organizationProfileDto.setRepresentativeNum(organization.getRepresentativeNum());
                organizationProfileDto.setKind(organization.getKind());
                organizationProfileDto.setHistory(organization.getHistory());
                organizationProfileDto.setOrganizationEmail(organization.getOrganizationEmail());
                organizationProfileDto.setGreetings(organization.getGreetings());

                profileDto.setOrganizationProfileDto(organizationProfileDto);

                return profileDto;

            } catch (Exception e) {
                System.err.println(e);
                return null;
            }
        }
    }

    @Override
    public List<NotificationResponseDto> getAllNotification() throws Exception {

        List<Notification> notifications = notificationRepository.findAllByReceiver(SecurityUtil.getCurrentMemberId().getEmail());
        List<NotificationResponseDto> notificationResponseDtos = new ArrayList<>();

        for (Notification notification : notifications) {
            if(!notification.getIsRead()) {
                NotificationResponseDto notificationResponseDto = new NotificationResponseDto();
                notificationResponseDto.setMessage(notification.getMessage());
                Proposal proposal = proposalRepository.findById(notification.getProposalId()).orElseThrow(() -> new Exception("proposal not found"));
                notificationResponseDto.setProposal(proposal);
                notificationResponseDto.setId(proposal.getId());

                notificationResponseDtos.add(notificationResponseDto);
            }
        }
        return notificationResponseDtos;
    }

    @Override
    public Boolean readNotification(Long notificationId) throws Exception {
        try {
            Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new Exception("notification not found"));
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean selectProposal(ProposalSelectDto proposalSelectDto) throws Exception {
        Proposal proposal = proposalRepository.findById(proposalSelectDto.getProposalId()).orElseThrow(() -> new Exception("proposal not found"));
        if(proposalSelectDto.getSelect()) {
            proposal.setIsApproved(true);
            proposalRepository.save(proposal);
            return true;
        } else {
            proposal.setIsApproved(false);
            proposalRepository.save(proposal);
            return false;
        }
    }

    @Override
    public List<ProposalProfileDto> sendProposal() throws Exception {
        List<Proposal> proposals = proposalRepository.findAllByCompanyEmail(SecurityUtil.getCurrentMemberId().getEmail());
        List<ProposalProfileDto> proposalProfileDtos = new ArrayList<>();

        for(Proposal proposal : proposals) {
            ProposalProfileDto proposalProfileDto = new ProposalProfileDto();
            proposalProfileDto.setId(proposal.getId());

            Organization organization = organizationRepository.findByEmail(SecurityUtil.getCurrentMemberId().getEmail()).orElseThrow(() -> new Exception("proposal not found"));
            proposalProfileDto.setCompanyName(organization.getOrganizationName());
            proposalProfileDto.setMessage(proposal.getMessage());
            proposalProfileDto.setPostId(proposal.getPostId());
            proposalProfileDto.setIsApproved(proposal.getIsApproved());
            proposalProfileDto.setUpdatedAt(proposal.getUpdatedAt());

            proposalProfileDtos.add(proposalProfileDto);
        }

        return proposalProfileDtos;
    }

    @Override
    public List<ProposalProfileDto> receiveProposal() throws Exception {
        List<Proposal> proposals = proposalRepository.findAllByOrganizationEmail(SecurityUtil.getCurrentMemberId().getEmail());
        List<ProposalProfileDto> proposalProfileDtos = new ArrayList<>();

        for(Proposal proposal : proposals) {
            ProposalProfileDto proposalProfileDto = new ProposalProfileDto();
            proposalProfileDto.setId(proposal.getId());

            Organization organization = organizationRepository.findByEmail(proposal.getCompanyEmail()).orElseThrow(() -> new Exception("proposal not found"));
            proposalProfileDto.setCompanyName(organization.getOrganizationName());
            proposalProfileDto.setMessage(proposal.getMessage());
            proposalProfileDto.setPostId(proposal.getPostId());
            proposalProfileDto.setIsApproved(proposal.getIsApproved());
            proposalProfileDto.setUpdatedAt(proposal.getUpdatedAt());

            proposalProfileDtos.add(proposalProfileDto);
        }

        return proposalProfileDtos;
    }
}