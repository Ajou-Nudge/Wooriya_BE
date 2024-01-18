package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.Proposal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ProfileDto profile(String email) throws Exception;

    List<NotificationResponseDto> getAllNotification() throws Exception;

    Boolean readNotification(Long notificationId) throws Exception;

    Boolean selectProposal(ProposalSelectDto proposalSelectDto) throws Exception;

    List<ProposalProfileDto> sendProposal() throws Exception;

    List<ProposalProfileDto> receiveProposal() throws Exception;

}