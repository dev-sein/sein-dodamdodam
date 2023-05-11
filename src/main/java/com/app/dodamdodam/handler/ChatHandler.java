package com.app.dodamdodam.handler;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.service.chatting.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChattingService chattingService;

    private final List<WebSocketSession> list = new ArrayList<>();

    // message : 받은 메세지 처리하는 메소드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);
        // payload : 전송되는 데이터를 의미

        for(WebSocketSession sess: list) {
            sess.sendMessage(message);
        }

//        Chatting chatting = objectMapper.readValue(payload, Chatting.class);
//        Room room = chattingService.findRoomByMemberId(chatting.getId());
//        room.handlerActions(session, chatting, chattingService);

//        메세지를 json형식을 통해서 웹소켓을 통해 서버로 보내면,
//        Handler는 이를받아 ObjectMapper를 통해서 해당 json 데이터를 Chatting.class에 맞게 파싱하여 Chatting 객체로 변환하고,
//        json 데이터에 들어있는 roomId를 이용해서 해당 채팅방을 찾아 handlerAction() 메서드를 실행
//        handlerAction() 메서드는 이 참여자가 현재 이미 채팅방에 접속된 상태인지, 아니면 이미 채팅에 참여해있는 상태인지를 판별하여,
//        만약 채팅방에 처음 참여하는거라면 session을 연결해줌과 동시에 메시지를 보낼것이고 아니라면 메시지를 해당 채팅방으로 보내게 될 것이다.
    }

    // connection established : session이 접속 했을때, 처리하는 메소드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    // connection closed : session이 접속을 해제했을 때, 처리하는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

    }

}
