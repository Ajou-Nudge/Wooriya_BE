package com.nudge.wooriya.controller;

import com.nudge.wooriya.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
