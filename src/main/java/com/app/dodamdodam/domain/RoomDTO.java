package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.QChatting;
import com.app.dodamdodam.service.chatting.ChattingService;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;

@Data
@Builder
public class RoomDTO {
    private Long id;
    private Long hostId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public void handlerActions(WebSocketSession session, ChattingDTO chattingDTO, ChattingService chattingService){
//        if (chattingDTO.getMessageType())
    }

//    public <T> void sendMessage(T message, ChattingService chatService) {
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
//    }

}
