package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.entity.event.EventReview;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.MemberStatus;
import com.app.dodamdodam.type.MemberType;
import com.app.dodamdodam.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
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

//    무한스크롤
    @Test
    public void findAllWithSearch_QueryDSLTest(){
        EventBoardSearch eventBoardSearch = new EventBoardSearch();

        Slice<EventBoard> result = eventBoardRepository.findAllWithSearch_QueryDSL(eventBoardSearch, PageRequest.of(1,5));
        result.stream().forEach(eventBoard -> log.info(eventBoard.toString()));
    }

    /*상세글 보기*/
    @Test
    public void findByIdTest(){
        eventBoardRepository.findById(101L).map(EventBoard::toString).ifPresent(log::info);
    }

    /* 삭제*/
    @Test
    public void deleteTest(){
        eventBoardRepository.findById(101L).ifPresent(eventBoardRepository::delete);
    }

    @Test
    public void findEventBoardById_QueryDSLTest(){
        eventBoardRepository.findEventBoardById_QueryDSL(101L)
                .ifPresent(eventBoard -> log.info(eventBoard.toString()));
    }

    /*리뷰 저장*/
    @Test
    public void reviewSaveTest(){
        EventBoard eventBoard = eventBoardRepository.findById(101L).get();
        Member member = memberRepository.findById(101L).get();
        for(int i = 0; i<20; i++){
            EventReview eventReview = new EventReview("test" + (i+1),eventBoard, member);
        }
    }

    @Test
    public void updateTest(){
        eventBoardRepository.findById(101L).ifPresent(eventBoard -> {
            eventBoard.setBoardTitle("수정제목1");
            eventBoard.setBoardContent("수정내용1");
        });
    }

}
