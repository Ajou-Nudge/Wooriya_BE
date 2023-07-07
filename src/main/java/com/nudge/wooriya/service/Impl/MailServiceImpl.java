package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

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
}
