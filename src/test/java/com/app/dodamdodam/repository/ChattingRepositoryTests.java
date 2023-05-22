//package com.app.dodamdodam.repository;
//
//import com.app.dodamdodam.entity.chatting.Chatting;
//import com.app.dodamdodam.entity.chatting.Room;
//import com.app.dodamdodam.repository.chatting.ChattingRepository;
//import com.app.dodamdodam.repository.member.MemberRepository;
//import com.app.dodamdodam.repository.room.RoomRepository;
//import com.app.dodamdodam.search.chatting.RoomSearch;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class ChattingRepositoryTests {
//    @Autowired
//    private ChattingRepository chattingRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    /* 채팅 추가하기 */
//    @Test
//    public void saveTest(){
//        for (int i=1; i<100; i++) {
//            Room room = new Room(199L, 199L + i);
//            memberRepository.findById(199L).ifPresent(member -> room.setMember(member));
//            Chatting chatting = new Chatting(199L, 199L + i, "199번이"  + (199 + i) + "번에게 보내는 메세지" + i);
//            chatting.setRoom(room);
//            chattingRepository.save(chatting);
//            roomRepository.save(room);
//        }
//    }
//
//
//    /* 룸 추가하기 */
//    @Test
//    public void roomSaveTest(){
//        Room room = new Room(1L, 2L);
//        memberRepository.findById(1L).ifPresent(member -> room.setMember(member));
//        roomRepository.save(room);
//    }
//
//    /* id로 내가 참여한 채팅 목록 가져오기*/
//    @Test
//    public void findChattingByMemberIdTest(){
//        Pageable pageable = PageRequest.of(0,10);
//        chattingRepository.findChattingByMemberId(pageable, 690L).stream().map(Chatting::toString).forEach(log::info);
//
//    }
//
//    /* id로 내가 참여한 룸 목록 가져오기*/
//    @Test
//    public void findRoomByMemberIdTest(){
//        Pageable pageable = PageRequest.of(0,10);
//        roomRepository.findRoomByMemberId_QueryDSL(pageable, 690L).stream().map(Room::toString).forEach(log::info);
//    }
//
//
//    @Test
//    public void updateTest(){
//////        시나리오 : 690번 그룹 멤버가 채팅방에 입장하면 chattingStatus id 32, 35만 Read로 바뀐다.
//////        11번 그룹멤버의 정보를 groupMember에 담아준다.
////        Chatting chatting = chattingRepository.findById(690L).get();
////        log.info(chatting.toString());
////        List<ChattingStatus> chattingStatusList = new ArrayList<>();
////        chattingStatusList.addAll(chattingStatusRepositoryImpl.findByGroupMemberId(groupMember));
////        chattingStatusList.stream().forEach(v-> v.update(ReadStatus.READ));
//    }
//
//    /* 검색 */
//    @Test
//    public void findRoomSearchWithPaging_QueryDSL_Test(){
//        RoomSearch roomSearch = new RoomSearch();
////        roomSearch.setMemberName("테스트1");
////        roomSearch.setChattingContent("1번이2번에게 보내는 메세지1");
//        Slice<Room> roomPage = roomRepository.findRoomSearchWithPaging_QueryDSL(roomSearch, PageRequest.of(1, 5));
//        log.info("============"+roomSearch.getChattingContent());
//
//        roomPage.stream().forEach(room -> log.info(room.toString()));
//    }
//
//
//
//}
//
