package com.app.dodamdodam.handler;

import com.app.dodamdodam.domain.ChattingDTO;
import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.service.chatting.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("{}", payload);
        ChattingDTO chatting = objectMapper.readValue(payload, ChattingDTO.class);

        RoomDTO room = chatService.findRoomById(chatting.getId());
        room.handlerActions(session, chatting, chatService);
    }
}
