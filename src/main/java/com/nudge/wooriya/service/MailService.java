package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.ConfirmCodeDto;
import com.nudge.wooriya.data.dto.ProposalRequestDto;
import com.nudge.wooriya.data.entity.Proposal;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface MailService {
    String sendMail(String mailAddress);

    Boolean sendConfirmCode(ConfirmCodeDto confirmcodeDto);

    Boolean sendProposalMail(ProposalRequestDto proposalRequestDto) throws Exception;

    Boolean sendProposalResultMail(Proposal proposal) throws Exception;

    Boolean verifyConfirmCode(ConfirmCodeDto confirmCodeDto) throws Exception;
}