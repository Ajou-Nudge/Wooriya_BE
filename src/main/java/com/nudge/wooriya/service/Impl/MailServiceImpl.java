package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.JwtTokenProvider;
import com.nudge.wooriya.data.dao.MemberDAO;
import com.nudge.wooriya.data.entity.Member;
import com.nudge.wooriya.data.repository.UserRepository;
import com.nudge.wooriya.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String verifyConfirmCode(String confirmCode) throws Exception {

        String userName = jwtTokenProvider.getUserNameFromEmailVerificationToken(confirmCode);

        Member member = userRepository.findByUserName(userName).orElseThrow(() -> new Exception("member not found"));
        member.setVerify(true);
        memberDAO.join(member);
        return "이메일 인증 완료";
    }
}
