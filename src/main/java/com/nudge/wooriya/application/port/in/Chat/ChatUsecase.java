package com.nudge.wooriya.application.port.in.Chat;

import com.nudge.wooriya.application.port.in.Chat.dto.*;
import com.nudge.wooriya.adapter.out.persistence.MongoEntity.Session;

import java.util.List;

public interface ChatUsecase {
    Boolean sendChat(ChatRequestDto chatRequestDto);

    List<Session> getSessions();

    List<ChatResponseDto> getChats(SessionRequestDto sessionRequestDto);
}