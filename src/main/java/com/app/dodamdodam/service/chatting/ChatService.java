package com.app.dodamdodam.service.chatting;

import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.entity.chatting.QRoom;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.repository.room.RoomRepository;
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
import static com.app.dodamdodam.entity.chatting.QRoom.room;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, RoomDTO> chatRooms;
    private RoomRepository roomRepository;

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

    // 현재 시퀀스 가져오기
    public Room getCurrentSequence() {
        return roomRepository.getCurrentSequence();
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

    public RoomDTO createRoom(Long hostId, Long havingId) {
        Room currentSequence = getCurrentSequence();
        Long roomId = currentSequence != null ? currentSequence.getId() + 1 : 1L;
        RoomDTO chatRoom = RoomDTO.builder()
                .id(roomId)
                .hostId(hostId)
                .havingId(havingId)
                .build();
        chatRooms.put(String.valueOf(roomId), chatRoom);//아이디와 room 정보를 Map 에 저장

        log.info("=================================", currentSequence.toString());
        log.info("=================================", roomId.toString());
        log.info("=================================", chatRoom.toString());
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
