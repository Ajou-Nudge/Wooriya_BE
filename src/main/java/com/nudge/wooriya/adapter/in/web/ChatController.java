package com.nudge.wooriya.adapter.in.web;

import com.nudge.wooriya.application.port.in.Chat.dto.*;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Session;
import com.nudge.wooriya.application.port.in.Chat.ChatUsecase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Chat", description = "채팅 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatUsecase chatService;

    @Operation(summary = "getSessions", description = "[Token O] 내 채팅방 목록")
    @GetMapping("/")
    public ResponseEntity<List<Session>> getSessions() {
        List<Session> result = chatService.getSessions();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "getChats", description = "[Token O] 이메일 or sessionId로 대화 목록 조회")
    @PostMapping("/get")
    public ResponseEntity<List<ChatResponseDto>> getChats(@RequestBody SessionRequestDto sessionRequestDto) {
        List<ChatResponseDto> result = chatService.getChats(sessionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "sendChat", description = "[Token O] 채팅 메시지 전송, sessionId or receiverEmail 둘 중 하나는 필수로 전달!")
    @PostMapping("/send")
    public ResponseEntity<Boolean> sendChat(@RequestBody ChatRequestDto chatRequestDto) {
        Boolean result = chatService.sendChat(chatRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}