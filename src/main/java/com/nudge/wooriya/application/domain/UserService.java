package com.nudge.wooriya.application.domain;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Company;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Notification;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Organization;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import com.nudge.wooriya.adapter.out.persistence.Repo.CompanyRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.NotificationRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.OrganizationRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.ProposalRepository;
import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.in.User.dto.*;
import com.nudge.wooriya.application.port.in.Mail.MailUsecase;
import com.nudge.wooriya.application.port.in.User.UserUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserUsecase {

    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final ProposalRepository proposalRepository;
    private final NotificationRepository notificationRepository;
    private final MailUsecase mailUsecase;

    @Autowired
    public UserService(CompanyRepository companyRepository, OrganizationRepository organizationRepository,
                       ProposalRepository proposalRepository,
                       NotificationRepository notificationRepository, MailUsecase mailUsecase) {
        this.companyRepository = companyRepository;
        this.organizationRepository = organizationRepository;
        this.proposalRepository = proposalRepository;
        this.notificationRepository = notificationRepository;
        this.mailUsecase = mailUsecase;
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
                organizationProfileDto.setRepresentativeEmail(organization.getRepresentativeEmail());
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
            mailUsecase.sendProposalResultMail(proposal);
            return true;
        } else {
            proposal.setIsApproved(false);
            proposalRepository.save(proposal);
            mailUsecase.sendProposalResultMail(proposal);
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

            String companyName = companyRepository.findByEmail(SecurityUtil.getCurrentMemberId().getEmail()).orElseThrow(() -> new Exception("proposal not found")).getCompanyName();
            proposalProfileDto.setCompanyName(companyName);
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

            String companyName = companyRepository.findByEmail(proposal.getCompanyEmail()).orElseThrow(() -> new Exception("company not found")).getCompanyName();
            proposalProfileDto.setCompanyName(companyName);
            proposalProfileDto.setMessage(proposal.getMessage());
            proposalProfileDto.setPostId(proposal.getPostId());
            proposalProfileDto.setIsApproved(proposal.getIsApproved());
            proposalProfileDto.setUpdatedAt(proposal.getUpdatedAt());

            proposalProfileDtos.add(proposalProfileDto);
        }

        return proposalProfileDtos;
    }
}