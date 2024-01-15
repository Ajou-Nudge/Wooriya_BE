package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.config.security.TokenInfo;
import com.nudge.wooriya.data.dao.CompanyDAO;
import com.nudge.wooriya.data.dao.OrganizationDAO;
import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.*;
import com.nudge.wooriya.data.repository.*;
import com.nudge.wooriya.service.AuthService;
import com.nudge.wooriya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final ProposalRepository proposalRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public UserServiceImpl(CompanyRepository companyRepository, OrganizationRepository organizationRepository,
                           ProposalRepository proposalRepository,
                           NotificationRepository notificationRepository) {
        this.companyRepository = companyRepository;
        this.organizationRepository = organizationRepository;
        this.proposalRepository = proposalRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ProfileDto profile(String email) throws Exception {
        boolean isCompanyExist = companyRepository.existsByEmail(email);

        if (isCompanyExist) {
            Company company = companyRepository.findById(email).orElseThrow(() -> new Exception("member not found"));
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
                Organization organization = organizationRepository.findById(email).orElseThrow(() -> new Exception("member not found"));
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
}