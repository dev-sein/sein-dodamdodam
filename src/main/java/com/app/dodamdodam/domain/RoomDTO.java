package com.app.dodamdodam.domain;

import com.app.dodamdodam.service.chatting.ChatService;
import com.app.dodamdodam.service.chatting.ChatServiceImpl;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Slf4j
public class RoomDTO {
    private Long id;
    private Long hostId;
    private Long havingId;
    private MemberDTO memberDTO;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Integer chattingUnreadCount;
    private Set<WebSocketSession> sessions = new HashSet<>();


    @QueryProjection
    public RoomDTO(Long id, Long hostId, Long havingId, MemberDTO memberDTO, LocalDateTime createdDate, LocalDateTime updatedDate, Integer chattingUnreadCount, Set<WebSocketSession> sessions) {
        this.id = id;
        this.hostId = hostId;
        this.havingId = havingId;
        this.memberDTO = memberDTO;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.chattingUnreadCount = chattingUnreadCount;
        this.sessions = sessions;
    }




    public void handlerActions(WebSocketSession session, ChattingDTO chattingDTO, ChatServiceImpl chatService) {
        if (chattingDTO.getMessageType().equals(ChattingDTO.MessageType.ENTER)) {
            sessions.add(session);
//            sessions.put(chattingDTO.getSenderMemberId(), session);
            chattingDTO.setChattingContent(chattingDTO.getSenderMemberId() + "님이 입장했습니다.");
        }
        log.info("===============================================================================");
        log.info("===============================================================================");
        log.info("==================================== handleMessage ============================");
        log.info("===============================================================================");
        log.info("===============================================================================");
        log.info(sessions.toString());
        sendMessage(chattingDTO, chatService);

    }

    private <T> void sendMessage(T message, ChatServiceImpl chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
        log.info("===============================================================================");
        log.info("===============================================================================");
        log.info("==================================== send =====================================");
        log.info("===============================================================================");
        log.info("===============================================================================");
        log.info("===============================================================================");
        log.info(sessions.toString());
    }

}
