package com.nudge.wooriya.controller;

import com.nudge.wooriya.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody String mailAddress) {
        return mailService.sendMail(mailAddress);
    }

//    @PostMapping("/proposal")
//    public ResponseEntity<Boolean> sendProposalMail(@RequestBody String mailAddress) throws MessagingException {
//        return ResponseEntity.status(HttpStatus.OK).body(mailService.sendProposalMail(mailAddress));
//    }
}
