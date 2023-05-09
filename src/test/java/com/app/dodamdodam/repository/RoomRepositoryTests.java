package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.entity.free.FreeBoard;
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
public class RoomRepositoryTests {
    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomRepository roomRepository;

    /* id로 내가 참여한 채팅 목록 가져오기*/
    @Test
    public void findByIdTest(){
        Pageable pageable = PageRequest.of(0,10);
        roomRepository.findRoomByMemberId(pageable, 3L).stream().map(Room::toString).forEach(log::info);
    }

}
