package com.nudge.wooriya.service;

import jakarta.mail.MessagingException;

public interface MailService {
    String sendMail(String mailAddress);

    String sendConfirmCode(String mailAddress);

    Boolean sendProposalMail(String mailAddress) throws MessagingException;

    String verifyConfirmCode(String confirmCode) throws Exception;
}