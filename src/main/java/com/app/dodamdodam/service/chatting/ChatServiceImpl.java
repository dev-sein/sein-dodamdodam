package com.app.dodamdodam.service.chatting;

import com.app.dodamdodam.domain.ChattingDTO;
import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.repository.chatting.ChattingRepository;
import com.app.dodamdodam.repository.room.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final RoomRepository roomRepository;
    private final ChattingRepository chattingRepository;
    private final ObjectMapper objectMapper;
    private Map<String, RoomDTO> chatRooms;

//    @Override @Transactional
//    public void register(SuggestDTO suggestDTO, Long memberId) {
//
//        memberRepository.findById(memberId).ifPresent(
//                member -> suggestDTO.setMemberDTO(toMemberDTO(member))
//        );
//
//        suggestRepository.save(toSuggestEntity(suggestDTO));
//
//    }

    /*채팅매세지 저장*/
//    @Override @Transactional
//    public void saveMessage(ChattingDTO chattingDTO, Long memberId, Long roomId) {
//        Long hostId = roomRepository.findById(memberId);
//        chattingDTO.setSenderMemberId(hostId);
//    }

    /*내가 참여한 채팅룸 목록*/
    @Override
    public List<RoomDTO> getRoomByMemberId(Pageable pageable, Long memberId) {
        return roomRepository.findRoomByMemberId_QueryDSL(pageable, memberId).stream().map(room -> toRoomDTO(room)).collect(Collectors.toList());
    }

    /*내가 쓴 채팅 목록*/
    @Override
    public List<ChattingDTO> getChattingByRoomId(Pageable pageable, Long roomId) {
        return chattingRepository.findChattingByMemberId(pageable, roomId).stream().map(chatting -> toChattingDTO(chatting)).collect(Collectors.toList());
    }

    /*채팅룸 저장*/
    public RoomDTO createRoom(Long roomId) {
        RoomDTO chatRoom = RoomDTO.builder()
                .id(roomId)
                .build();
        chatRooms.put("roomId", chatRoom);
        log.info(chatRoom.toString());
        return chatRoom;
    }

    /*채팅내역 저장 로직*/
    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
