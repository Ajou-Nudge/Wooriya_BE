package com.nudge.wooriya.service.Impl;

import com.nudge.wooriya.config.security.SecurityUtil;
import com.nudge.wooriya.data.dto.ChatRequestDto;
import com.nudge.wooriya.data.dto.ChatResponseDto;
import com.nudge.wooriya.data.dto.SessionRequestDto;
import com.nudge.wooriya.data.entity.Chat;
import com.nudge.wooriya.data.entity.Session;
import com.nudge.wooriya.data.repository.ChatRepository;
import com.nudge.wooriya.data.repository.SessionRepository;
import com.nudge.wooriya.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final SessionRepository sessionRepository;

    public ChatServiceImpl(ChatRepository chatRepository, SessionRepository sessionRepository) {
        this.chatRepository = chatRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Boolean sendChat(ChatRequestDto chatRequestDto) {
        Chat chat = new Chat();

        if(chatRequestDto.getSessionId() == null) {
            Session session = sessionRepository.findByMembersContains(SecurityUtil.getCurrentMemberId().getEmail(), chatRequestDto.getReceiverEmail());
            if(session != null) {
                chat.setSessionId(session.getId());
            } else {
                List<String> members = new ArrayList<>();
                members.add(SecurityUtil.getCurrentMemberId().getEmail());
                members.add(chatRequestDto.getReceiverEmail());

                Session createdSession = new Session();
                createdSession.setMembers(members);
                String sessionId = sessionRepository.save(createdSession).getId();

                chat.setSessionId(sessionId);
            }
        }
        else {
            chat.setSessionId(chatRequestDto.getSessionId());
        }

        chat.setMessage(chatRequestDto.getMessage());
        chat.setSenderEmail(SecurityUtil.getCurrentMemberId().getEmail());
        chat.setTimestamp(new Date());
        chatRepository.save(chat);

        return true;
    }


    @Override
    public List<ChatResponseDto> getChats(SessionRequestDto sessionRequestDto) {
        String sessionId = null;

        if(sessionRequestDto.getSessionId() == null) {
            Session session = sessionRepository.findByMembersContains(SecurityUtil.getCurrentMemberId().getEmail(), sessionRequestDto.getEmail());
            if(session != null) {
                sessionId = session.getId();
            } else {
                // exception
            }
        }
        else {
            sessionId = sessionRequestDto.getSessionId();
        }

        List<ChatResponseDto> chatResponseDtos = new ArrayList<>();
        List<Chat> chats = chatRepository.findBySessionId(sessionId);

        for(Chat chat : chats) {
            ChatResponseDto chatResponseDto = new ChatResponseDto();
            chatResponseDto.setMessage(chat.getMessage());
            chatResponseDto.setSenderEmail(chat.getSenderEmail());
            chatResponseDto.setSessionId(chat.getSessionId());

            chatResponseDtos.add(chatResponseDto);
        }
        return chatResponseDtos;
    }

    @Override
    public List<Session> getSessions() {
        List<Session> sessions = sessionRepository.findByMembersContains(SecurityUtil.getCurrentMemberId().getEmail());
        return sessions;
    }
}
