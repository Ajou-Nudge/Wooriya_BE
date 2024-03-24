package com.nudge.wooriya.application.port.in.Mail;

import com.nudge.wooriya.application.port.in.Mail.dto.*;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;

public interface MailUsecase {
    String sendMail(String mailAddress);

    Boolean sendConfirmCode(MailConfirmCodeDto confirmcodeDtoMail);

    Boolean sendProposalMail(Long postId, MailProposalDto proposalRequestDto) throws Exception;

    Boolean sendProposalResultMail(Proposal proposal) throws Exception;

    Boolean verifyConfirmCode(MailConfirmCodeDto mailConfirmCodeDto) throws Exception;
}