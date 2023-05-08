package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.chatting.chatting.ChattingRepository;
import com.app.dodamdodam.repository.chatting.room.RoomRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ChattingRepositoryTests {
    @Autowired
    private ChattingRepository chattingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        for (int i=1; i<=100; i++){
            Chatting chatting = new Chatting(1L, 2L + i, "채팅 글" + i);
            memberRepository.findById(5L).ifPresent(member -> chatting.setRoom());

        }
    }


}

