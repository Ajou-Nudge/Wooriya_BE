package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.data.dao.MemberDAO;
import com.nudge.wooriya.data.entity.Member;
import com.nudge.wooriya.data.repository.UserRepository;
import com.nudge.wooriya.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private MemberDAO memberDAO;

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
    public String sendConfirmCode(String mailAddress) {

        boolean isExist = userRepository.existsByEmail(mailAddress);
        Member member = new Member();

        if (isExist) throw new AlreadyBuiltException("이미 가입된 이메일입니다.");
        else {
            member.setEmail(mailAddress);
            member.setPassword("password");
            member.setRole("role");
            member.setVerify(false);
            memberDAO.join(member);
        }

        String emailVerificationToken = jwtTokenProvider.createEmailVerificationToken(member.getUserName(), member.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Wooriya <test@wooriya.com>");
        message.setTo(mailAddress);
        message.setSubject("[Wooriya] 회원가입 이메일 인증");
        message.setText("[Wooriya] 회원가입을 계속하려면, 해당 링크를 클릭해서 이메일 주소 인증을 완료해주세요!\n"
                +"http://localhost:8080/user/join/confirm-mail/"+emailVerificationToken);
        mailSender.send(message);

        System.out.println("Confirm Code: " + emailVerificationToken);
        return "인증코드 전송 완료";
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
                "                <img src=\"logo.svg\" alt=\"Logo\" width=\"100\" />\n" +
                "                <h1>\n" +
                "                    (변동사항) 제휴 제안자님 (변동사항), 우리야에서 제휴 제안이\n" +
                "                    왔어요!\n" +
                "                </h1>\n" +
                "            </div>\n" +
                "            <div class=\"body-content\">\n" +
                "                <p>\n" +
                "                    <strong\n" +
                "                        >(변동사항)  제휴 제안자님, 어디 단체의\n" +
                "                        누구누구(변동사항)</strong\n" +
                "                    >로부터 제휴 제안이 왔어요!!!\n" +
                "                    <br />\n" +
                "                    제휴 제안자의 요청 사항은 다음과 같아요\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                    <strong\n" +
                "                        >(변동사항) 사용자가 입력한 제휴 요청 사항\n" +
                "                        (변동사항)</strong\n" +
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
                "                <p>Nuge | 넛지 이메일</p>\n" +
                "                <p>대표: 민정근 | Phone: 정근님 번호 | Email: 정근님 번호</p>\n" +
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
    public String verifyConfirmCode(String confirmCode) throws Exception {

        String userName = jwtTokenProvider.getUserNameFromEmailVerificationToken(confirmCode);

        Member member = userRepository.findByUserName(userName).orElseThrow(() -> new Exception("member not found"));
        member.setVerify(true);
        memberDAO.join(member);
        return "이메일 인증 완료";
    }
}
