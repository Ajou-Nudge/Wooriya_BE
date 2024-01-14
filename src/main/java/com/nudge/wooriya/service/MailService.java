package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.ConfirmCodeDto;
import jakarta.mail.MessagingException;

public interface MailService {
    String sendMail(String mailAddress);

    Boolean sendConfirmCode(ConfirmCodeDto confirmcodeDto);

    Boolean sendProposalMail(String mailAddress) throws MessagingException;

    Boolean verifyConfirmCode(ConfirmCodeDto confirmCodeDto) throws Exception;
}