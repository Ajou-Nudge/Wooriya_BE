package com.nudge.wooriya.controller;

import com.nudge.wooriya.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name="Mail", description = "메일 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @Operation(summary = "sendMail", description = "[Token X] 그냥 메일 테스트용 API")
    @PostMapping("/send")
    public String sendMail(@RequestBody String mailAddress) {
        return mailService.sendMail(mailAddress);
    }

//    @PostMapping("/proposal")
//    public ResponseEntity<Boolean> sendProposalMail(@RequestBody String mailAddress) throws MessagingException {
//        return ResponseEntity.status(HttpStatus.OK).body(mailService.sendProposalMail(mailAddress));
//    }
}
