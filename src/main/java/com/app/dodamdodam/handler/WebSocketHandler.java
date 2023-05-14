package com.app.dodamdodam.handler;

import com.app.dodamdodam.domain.ChattingDTO;
import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.entity.chatting.QRoom;
import com.app.dodamdodam.entity.member.QMember;
import com.app.dodamdodam.service.chatting.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import static com.app.dodamdodam.entity.chatting.QRoom.room;

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

//        RoomDTO roomDTO = chatService.findRoomById(chatting.getId());
        RoomDTO roomDTO = chatService.findRoomByMemberId(chatting.getSenderMemberId());


//    세션 가지고 있음
        roomDTO.handlerActions(session, chatting, chatService);
    }
}
