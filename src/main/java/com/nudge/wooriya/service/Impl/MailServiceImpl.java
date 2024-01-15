package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dto.ConfirmCodeDto;
import com.nudge.wooriya.data.dto.ProposalRequestDto;
import com.nudge.wooriya.data.entity.Company;
import com.nudge.wooriya.data.entity.EmailConfirm;
import com.nudge.wooriya.data.entity.Organization;
import com.nudge.wooriya.data.entity.ProposalPost;
import com.nudge.wooriya.data.repository.CompanyRepository;
import com.nudge.wooriya.data.repository.EmailConfirmRepository;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import com.nudge.wooriya.data.repository.ProposalPostRepository;
import com.nudge.wooriya.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
public class MailServiceImpl implements MailService {
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
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
    public Boolean sendConfirmCode(ConfirmCodeDto confirmcodeDto) {

        boolean isCompanyExist = companyRepository.existsByEmail(confirmcodeDto.getEmail());
        boolean isOrganizationExist = organizationRepository.existsByEmail(confirmcodeDto.getEmail());

        if (isCompanyExist && isOrganizationExist) {
            return false;
        }
        else {
            EmailConfirm emailConfirm = new EmailConfirm();
            String confirmCode = generateConfirmCode();
            emailConfirm.setEmail(confirmcodeDto.getEmail());
            emailConfirm.setConfirmCode(confirmCode);
            emailConfirm.setIsVerify(false);
            emailConfirm.setTimestamp(LocalDateTime.now());

            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("Wooriya <test@wooriya.com>");
                message.setTo(confirmcodeDto.getEmail());
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
    public Boolean sendProposalMail(ProposalRequestDto proposalRequestDto) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        ProposalPost proposalPost = proposalPostRepository.findById(proposalRequestDto.getPostId()).orElseThrow(() -> new Exception("post not found"));
        Company company = companyRepository.findById(SecurityUtil.getCurrentMemberId().getEmail()).orElseThrow(() -> new Exception("company not found"));
        String organizationName = organizationRepository.findById(proposalPost.getAuthor()).get().getOrganizationName();

        String htmlContent = readHtmlTemplate("src/main/resources/static/proposalMail.html");

        String processedHtmlContent = htmlContent
                .replace("{organizationName}",organizationName)
                .replace("{companyName}", company.getCompanyName())
                .replace("{message}", proposalRequestDto.getMessage());

        helper.setFrom("Wooriya <test@wooriya.com>");
        helper.setTo(proposalPost.getAuthor());
        helper.setSubject("[Wooriya] 제휴 제안");
        helper.setText(processedHtmlContent, true);

        mailSender.send(mimeMessage);

        return true;
    }

    @Override
    public Boolean verifyConfirmCode(ConfirmCodeDto confirmCodeDto) throws Exception {
        EmailConfirm emailConfirm = emailConfirmRepository.findById(confirmCodeDto.getEmail()).orElseThrow(() -> new Exception("member not found"));
        if(emailConfirm.getConfirmCode().equals(confirmCodeDto.getConfirmCode())) {
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
