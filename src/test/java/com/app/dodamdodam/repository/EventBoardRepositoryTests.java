package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.file.EventFileRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

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
//        Address address = new Address("test-address", "test-detail");
//        Member member = new Member("test1234", "1234", "테스트", "test1234@gmail.com", "01012341234", address, MemberStatus.NORMAL, MemberType.GENERAL, Role.MEMBER);
//
//        memberRepository.save(member);

        for(int i= 1; i<= 10; i ++){
//            EventBoard eventBoard = new EventBoard("테스트제목" + i, "테스트내용"+ i);
            EventBoard eventBoard1 = EventBoard.builder()
                    .boardTitle("이벤트 게시글 제목" + i)
                    .boardContent("이벤트 게시글 내용" + i)
                    .eventAddress("서울시")
                    .eventAddressDetail("강남구")
                    .eventBusinessEmail("test1@naver.com")
                    .eventBusinessName("기업이름" + i)
                    .eventBusinessNumber("10000" + i)
                    .eventBusinessTel("01012341234")
                    .eventStartDate((LocalDate.now()))
                    .eventEndDate(LocalDate.of(2023,6,20))
                    .eventLikeNumber(0)
                    .build();
            memberRepository.findById(201L).ifPresent(member1 -> eventBoard1.setMember(member1));
//            eventBoard.setMember(member);

//            for(int j = 0; j < 5; j ++){
//                EventFile eventFile = new EventFile(UUID.randomUUID().toString(), "test" + i+1, 10L, eventBoard,500, "");
//                eventFileRepository.save(eventFile);
//            }

            eventBoardRepository.save(eventBoard1);
        }
    }

//


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
//
//    /*리뷰 저장*/
//    @Test
//    public void reviewSaveTest(){
//        EventBoard eventBoard = eventBoardRepository.findById(101L).get();
//        Member member = memberRepository.findById(101L).get();
//        for(int i = 0; i<20; i++){
//            EventReview eventReview = new EventReview("test" + (i+1),eventBoard, member);
//        }
//    }

//    /*리뷰 저장*/
//    @Test
//    public void reviewSaveTest(){
//        EventBoard eventBoard = eventBoardRepository.findById(101L).get();
//        Member member = memberRepository.findById(101L).get();
//        for(int i = 0; i<20; i++){
//            EventReview eventReview = new EventReview("test" + (i+1),eventBoard, member);
//        }
//    }

    @Test
    public void updateTest(){
        eventBoardRepository.findById(101L).ifPresent(eventBoard -> {
            eventBoard.setBoardTitle("수정제목1");
            eventBoard.setBoardContent("수정내용1");
        });
    }

}
