package com.app.dodamdodam.service.chatting;

import com.app.dodamdodam.domain.ChattingDTO;
import com.app.dodamdodam.domain.MemberDTO;
import com.app.dodamdodam.domain.RoomDTO;
import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChatService {
//    private final ObjectMapper objectMapper;
//    private Map<String, RoomDTO> chatRooms;
//    private RoomRepository roomRepository;

//    @PostConstruct
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }


//    public List<RoomDTO> findAllRoom() {
//        return new ArrayList<>(chatRooms.values());
//    }

//    맴버 정보를 이용해 해당 맴버가 소속 된 룸을 모두 찾아서 리스트로 반환
//    public RoomDTO findRoomByMemberId(String roomId) {
//        return chatRooms.get(roomId);
//    }


// 현재 시퀀스 가져오기
//    public Room getCurrentSequence() {
//        return roomRepository.getCurrentSequence();
//    }

//    public RoomDTO createRoom(String name) {
//        String randomId = UUID.randomUUID().toString();
//        RoomDTO chatRoom = RoomDTO.builder()
//                .id(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        return chatRoom;
//    }

//    채팅방 저장 로직
//    public RoomDTO createRoom(Long id) {
//        String roomId = String.valueOf(id);
//        RoomDTO chatRoom = RoomDTO.builder()
//                .id(roomId)
//                .build();
//        chatRooms.put(roomId, chatRoom);
//        log.info(chatRoom.toString());
//        return chatRoom;
//    }

//    public RoomDTO createRoom(Long hostId, Long havingId) {
//        Room currentSequence = getCurrentSequence();
//        Long roomId = currentSequence != null ? currentSequence.getId() + 1 : 1L;
//        RoomDTO chatRoom = RoomDTO.builder()
//                .id(roomId)
//                .hostId(hostId)
//                .havingId(havingId)
//                .build();
//        chatRooms.put(String.valueOf(roomId), chatRoom);//아이디와 room 정보를 Map 에 저장
//
//        log.info("=================================", currentSequence.toString());
//        log.info("=================================", roomId.toString());
//        log.info("=================================", chatRoom.toString());
//        return chatRoom;
//    }

//    채팅방 생성
//    public void saveMessage(ChattingDTO chattingDTO, Long memberId, Long roomId);

//    내가 참여한 채팅룸 목록
    public List<RoomDTO> getRoomByMemberId(Pageable pageable, Long memberId);

//    roomId로 채팅 목록 불러오기
    public List<ChattingDTO> getChattingByRoomId(Pageable pageable, Long roomId);

    default RoomDTO toRoomDTO(Room room){
        return RoomDTO.builder()
                .id(room.getId())
                .hostId(room.getHostId())
                .havingId(room.getHavingId())
                .memberDTO(toMemberDTO(room.getMember()))
                .createdDate(room.getCreatedDate())
                .createdDate(room.getUpdatedDate())
                .build();
    }

    default ChattingDTO toChattingDTO(Chatting chatting){
        return ChattingDTO.builder()
                .id(chatting.getId())
                .chattingContent(chatting.getChattingContent())
                .senderMemberId(chatting.getSenderMemberId())
                .receiverMemberId(chatting.getReceiverMemberId())
                .roomDTO(toRoomDTO(chatting.getRoom()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder().id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberName(member.getMemberName())
                .memberId(member.getMemberId())
                .memberPassword(member.getMemberPassword())
                .memberPhone(member.getMemberPhone())
                .memberStatus(member.getMemberStatus())
                .memberPoint(member.getMemberPoint())
                .participationCount(member.getParticipationCount())
                .address(member.getAddress())
                .build();
    }



}
