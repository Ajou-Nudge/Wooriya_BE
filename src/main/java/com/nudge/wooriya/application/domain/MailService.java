package com.nudge.wooriya.application.domain;

import com.nudge.wooriya.adapter.out.persistence.MongoEntity.EmailConfirm;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Proposal;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.ProposalPost;
import com.nudge.wooriya.common.config.security.SecurityUtil;
import com.nudge.wooriya.application.port.in.Mail.dto.*;
import com.nudge.wooriya.adapter.out.persistence.Repo.CompanyRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.EmailConfirmRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.OrganizationRepository;
import com.nudge.wooriya.adapter.out.persistence.Repo.ProposalPostRepository;
import com.nudge.wooriya.application.port.in.Mail.MailUsecase;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
public class MailService implements MailUsecase {
    @Autowired
    private ProposalPostRepository proposalPostRepository;
    @Autowired
    private EmailConfirmRepository emailConfirmRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String sendMail(String mailAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Wooriya <test@wooriya.com>");
        message.setTo(mailAddress);
        message.setSubject("[Wooriya] 이메일 테스트");
        message.setText("이메일 테스트");
        mailSender.send(message);
        return "전송 완료";
    }

    @Override
    public Boolean sendConfirmCode(MailConfirmCodeDto confirmcodeDtoMail) {

        boolean isCompanyExist = companyRepository.existsByEmail(confirmcodeDtoMail.getEmail());
        boolean isOrganizationExist = organizationRepository.existsByEmail(confirmcodeDtoMail.getEmail());

        if (isCompanyExist && isOrganizationExist) {
            return false;
        }
        else {
            EmailConfirm emailConfirm = new EmailConfirm();
            String confirmCode = generateConfirmCode();
            emailConfirm.setEmail(confirmcodeDtoMail.getEmail());
            emailConfirm.setConfirmCode(confirmCode);
            emailConfirm.setIsVerify(false);
            emailConfirm.setTimestamp(LocalDateTime.now());

            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("Wooriya <test@wooriya.com>");
                message.setTo(confirmcodeDtoMail.getEmail());
                message.setSubject("[Wooriya] 회원가입 이메일 인증");
                message.setText("[Wooriya] 회원가입을 계속하려면 웹으로 돌아가 해당 인증코드를 입력해주세요!\n"
                        +confirmCode);
                mailSender.send(message);
            } catch (MailException e) {
                System.err.println(e);
                return false;
            }
            emailConfirmRepository.save(emailConfirm);
            return true;
        }
    }

    @Override
    public Boolean sendProposalMail(Long postId, MailProposalDto mailProposalDto) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        ProposalPost proposalPost = proposalPostRepository.findById(postId).orElseThrow(() -> new Exception("post not found"));
        String companyName = companyRepository.findById(SecurityUtil.getCurrentMemberId().getEmail()).orElseThrow(() -> new Exception("company not found")).getCompanyName();
        String organizationName = organizationRepository.findById(proposalPost.getAuthor()).get().getOrganizationName();

        String htmlContent = readHtmlTemplate("src/main/resources/static/proposalMail.html");

        String processedHtmlContent = htmlContent
                .replace("{organizationName}",organizationName)
                .replace("{companyName}", companyName)
                .replace("{message}", mailProposalDto.getMessage());

        helper.setFrom("Wooriya <test@wooriya.com>");
        helper.setTo(proposalPost.getAuthor());
        helper.setSubject("[Wooriya] 제휴 제안");
        helper.setText(processedHtmlContent, true);

        mailSender.send(mimeMessage);

        return true;
    }

    @Override
    public Boolean sendProposalResultMail(Proposal proposal) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        String htmlContent = readHtmlTemplate("src/main/resources/static/proposalResultMail.html");

        String organizationName = organizationRepository.findById(proposal.getOrganizationEmail()).get().getOrganizationName();
        String companyName = companyRepository.findById(proposal.getCompanyEmail()).get().getCompanyName();

        String processedHtmlContent = htmlContent
                .replace("{organizationName}", organizationName)
                .replace("{companyName}", companyName)
                .replace("{result}", proposal.getIsApproved() ? "승인" : "거절");

        helper.setFrom("Wooriya <test@wooriya.com>");
        helper.setTo(proposal.getCompanyEmail());
        helper.setSubject("[Wooriya] 제휴 제안 결과");
        helper.setText(processedHtmlContent, true);

        mailSender.send(mimeMessage);

        return true;
    }

    @Override
    public Boolean verifyConfirmCode(MailConfirmCodeDto mailConfirmCodeDto) throws Exception {
        EmailConfirm emailConfirm = emailConfirmRepository.findById(mailConfirmCodeDto.getEmail()).orElseThrow(() -> new Exception("member not found"));
        if(emailConfirm.getConfirmCode().equals(mailConfirmCodeDto.getConfirmCode())) {
            emailConfirm.setIsVerify(true);
            return true;
        } else {
            return false;
        }
    }

    private static String generateConfirmCode() {
        int codeLength = 6;
        StringBuilder generatedCode = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < codeLength; i++) {
            int randomDigit = random.nextInt(10);
            generatedCode.append(randomDigit);
        }

        return generatedCode.toString();
    }

    private String readHtmlTemplate(String templatePath) throws IOException {
        Path path = Paths.get(templatePath);
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }
}
