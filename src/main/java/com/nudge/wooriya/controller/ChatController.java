package com.nudge.wooriya.controller;

import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.Session;
import com.nudge.wooriya.service.ChatService;
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

    private final ChatService chatService;

    @Operation(summary = "getSessions", description = "[Token O] 내 채팅방 목록")
    @GetMapping("/")
    public ResponseEntity<List<Session>> getSessions() {
        List<Session> result = chatService.getSessions();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "getChatsBySessionId", description = "[Token O] sessionId로 대화 목록 조회")
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<ChatResponseDto>> getChatsBySessionId(@PathVariable Long sessionId) {
        List<ChatResponseDto> result = chatService.getChatsBySessionId(sessionId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "getChatsByEmail", description = "[Token O] 상대 이메일로 대화 목록 조회")
    @GetMapping("/{email}")
    public ResponseEntity<List<ChatResponseDto>> getChatsByEmail(@PathVariable String email) {
        List<ChatResponseDto> result = chatService.getChatsByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "sendChat", description = "[Token O] 채팅 메시지 전송, sessionId or receiverEmail 둘 중 하나는 필수로 전달!")
    @PostMapping("/send")
    public ResponseEntity<Boolean> sendChat(@RequestBody ChatRequestDto chatRequestDto) {
        Boolean result = chatService.sendChat(chatRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}