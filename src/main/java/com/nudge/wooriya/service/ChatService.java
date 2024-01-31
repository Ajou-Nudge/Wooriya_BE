package com.nudge.wooriya.service;

import com.nudge.wooriya.data.dto.*;
import com.nudge.wooriya.data.entity.Session;

import java.util.List;

public interface ChatService {
    Boolean sendChat(ChatRequestDto chatRequestDto);

    List<Session> getSessions();

    List<ChatResponseDto> getChatsByEmail(String email);

    List<ChatResponseDto> getChatsBySessionId(Long SessionId);

}