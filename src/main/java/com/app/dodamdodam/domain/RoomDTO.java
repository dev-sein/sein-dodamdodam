package com.app.dodamdodam.domain;

import com.app.dodamdodam.service.chatting.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoomDTO {
    private Long id;
    private Long hostId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public RoomDTO(Long hostId) {
        this.hostId = hostId;
    }

    public void handlerActions(WebSocketSession session, ChattingDTO chatting, ChatService chatService) {
        if (chatting.getMessageType().equals(ChattingDTO.MessageType.ENTER)) {
            sessions.add(session);
            chatting.setChattingContent(chatting.getSenderMemberId() + "님이 입장했습니다.");
        }
        sendMessage(chatting, chatService);

    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }

}
