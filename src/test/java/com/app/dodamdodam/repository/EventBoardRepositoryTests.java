package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.repository.board.event.EventBoardRepository;
import com.app.dodamdodam.repository.file.event.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class EventBoardRepositoryTests {
    @Autowired
    private EventBoardRepository eventBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EventFileRepository eventFileRepository;

    @Test
    public void saveTest(){
        Address address = new Address("1234", "test-address", "test-detail");
        Member member = new Member("test1234", "1234", "테스트", "test1234@gmail.com", "01012341234", address, MemberStatus.NORMAL, MemberType.GENERAL, Role.MEMBER);

        memberRepository.save(member);

        for(int i= 0; i< 10; i ++){

            EventBoard eventBoard = new EventBoard("테스트제목" + i, "테스트내용"+ i);
            eventBoard.setMember(member);

            for(int j = 0; j < 5; j ++){
                EventFile eventFile = new EventFile("test" +(i+1) + ".png", UUID.randomUUID().toString(), "test" + i+1, 10L, eventBoard);
                eventFileRepository.save(eventFile);
            }

            eventBoardRepository.save(eventBoard);
        }
    }


}
