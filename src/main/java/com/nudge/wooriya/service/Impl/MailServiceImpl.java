package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.data.dto.ConfirmCodeDto;
import com.nudge.wooriya.data.entity.EmailConfirm;
import com.nudge.wooriya.data.repository.CompanyRepository;
import com.nudge.wooriya.data.repository.EmailConfirmRepository;
import com.nudge.wooriya.data.repository.OrganizationRepository;
import com.nudge.wooriya.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Random;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
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
    public Boolean sendProposalMail(String mailAddress) throws MessagingException {
        String htmlBody = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <style>\n" +
                "            .email-container {\n" +
                "                font-family: Arial, sans-serif;\n" +
                "                max-width: 600px;\n" +
                "                margin: 0 auto;\n" +
                "                border: 1px solid #ddd;\n" +
                "                padding: 20px;\n" +
                "            }\n" +
                "            .header {\n" +
                "                text-align: center;\n" +
                "                padding-bottom: 20px;\n" +
                "            }\n" +
                "            .body-content {\n" +
                "                font-size: 14px;\n" +
                "                line-height: 1.6;\n" +
                "                color: #333;\n" +
                "            }\n" +
                "            .footer {\n" +
                "                font-size: 12px;\n" +
                "                text-align: center;\n" +
                "                color: #666;\n" +
                "                border-top: 1px solid #ddd;\n" +
                "                padding-top: 20px;\n" +
                "            }\n" +
                "            .button-link {\n" +
                "                display: inline-block;\n" +
                "                padding: 10px 20px;\n" +
                "                margin: 5px 0;\n" +
                "                background-color: #007bff;\n" +
                "                color: white;\n" +
                "                text-decoration: none;\n" +
                "                border-radius: 5px;\n" +
                "                font-weight: bold;\n" +
                "            }\n" +
                "            .button-link:hover {\n" +
                "                background-color: #0056b3;\n" +
                "            }\n" +
                "            .button-container {\n" +
                "                text-align: center;\n" +
                "                margin-top: 20px;\n" +
                "                margin-bottom: 20px;\n" +
                "            }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div class=\"email-container\">\n" +
                "            <div class=\"header\">\n" +
                "                <h1>\n" +
                "                    김선우님, 넛지에서 제휴(협찬) 제안이\n" +
                "                    왔어요!\n" +
                "                </h1>\n" +
                "            </div>\n" +
                "            <div class=\"body-content\">\n" +
                "                <p>\n" +
                "                    <strong\n" +
                "                        >김선우 님, 넛지(기업)</strong\n" +
                "                    >로부터 제휴(협찬) 제안이 도착했어요!\n" +
                "                    <br />\n" +
                "                    전달해주신 내용은 다음과 같아요\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    <strong\n" +
                "                        >안녕하세요 제휴 제안드립니다.</strong\n" +
                "                    >\n" +
                "                </p>\n" +
                "                <div class=\"button-container\">\n" +
                "                    <a\n" +
                "                        href=\"http://localhost:3000/organization/1\"\n" +
                "                        class=\"button-link\"\n" +
                "                        >게시한 글 확인하러 가기</a\n" +
                "                    >\n" +
                "                    <a\n" +
                "                        href=\"http://localhost:3000/organization/1\"\n" +
                "                        class=\"button-link\"\n" +
                "                        >제휴 현황 확인하러 가기</a\n" +
                "                    >\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"footer\">\n" +
                "                <p>Nudge | nudge335@gmail.com</p>\n" +
                "                <p>대표: 민정근 | Phone: 010-5561-3356 | Email: nudge335@gmail.com</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>\n";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        helper.setFrom("Wooriya <test@wooriya.com>");
        helper.setTo(mailAddress);
        helper.setSubject("[Wooriya] 제휴 제안");
        helper.setText(htmlBody, true);

        mailSender.send(mimeMessage);

        return true;
    }

    @Override
    public Boolean verifyConfirmCode(ConfirmCodeDto confirmCodeDto) throws Exception {
        EmailConfirm emailConfirm = emailConfirmRepository.findByEmail(confirmCodeDto.getEmail()).orElseThrow(() -> new Exception("member not found"));
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
}
