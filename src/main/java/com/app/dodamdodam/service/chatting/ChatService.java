package com.app.dodamdodam.service.chatting;

import com.app.dodamdodam.domain.RoomDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, RoomDTO> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<RoomDTO> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public RoomDTO findRoomById(Long memberId) {
        return chatRooms.get(memberId);
    }

//    public RoomDTO createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        RoomDTO chatRoom = RoomDTO.builder()
//                .id(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }

    public RoomDTO createRoom(Long hostId) {
        RoomDTO room = new RoomDTO(hostId);
        chatRooms.put(room.getId().toString(), room);
        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
