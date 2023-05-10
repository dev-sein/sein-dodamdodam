package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.repository.chatting.ChattingRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.room.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ChattingRepositoryTests {
    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomRepository roomRepository;

    /*채팅 추가하기*/
    @Test
    public void saveTest(){
        for (int i=1; i<=100; i++) {
            Room room = new Room(690L);
            memberRepository.findById(690L).ifPresent(member -> room.setMember(member));
            Chatting chatting = new Chatting(690L, 691L + i, "690번이"  + 691L + i + "번에게 보내는 메세지" + i);
            chatting.setRoom(room);
            chattingRepository.save(chatting);
            roomRepository.save(room);
        }
    }

    /* id로 내가 참여한 채팅 목록 가져오기*/
    @Test
    public void findChattingByMemberIdTest(){
        Pageable pageable = PageRequest.of(0,10);
        chattingRepository.findChattingByMemberId(pageable, 690L).stream().map(Chatting::toString).forEach(log::info);

    }

    /* id로 내가 참여한 룸 목록 가져오기*/
    @Test
    public void findRoomByMemberIdTest(){
        Pageable pageable = PageRequest.of(0,10);
        roomRepository.findRoomByMemberId(pageable, 690L).stream().map(Room::toString).forEach(log::info);
    }

//    @Test
//    public void findAll(){
//        chattingRepository.findAll().stream().map(Chatting::toString).forEach(log::info);
//    }



}

