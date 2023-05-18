package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.like.EventLikeRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class EventLikeRepositoryTests {
    @Autowired
    private EventLikeRepository eventLikeRepository;
    @Autowired
    private EventBoardRepository eventBoardRepository;
    @Autowired
    private MemberRepository memberRepository;

//
//    @Test
//    public void checkMemberLikesEventBoardTest(){

    @Test
    public void checkMemberLikesEventBoardTest(){
//        Optional<Member> member = memberRepository.findById(1L);
//        Optional<EventBoard> eventBoard = eventBoardRepository.findById(107L);
//
//        if(!member.isPresent() || !eventBoard.isPresent()) fail("멤버 혹은 리뷰 보드가 존재하지 않음");
//
//        checkMemberAlreadyLikes(member.get(), eventBoard.get());
//    }
//
//    private boolean checkMemberAlreadyLikes(Member member, EventBoard eventBoard){
//        return eventLikeRepository.checkMemberLikesEventBoard_QueryDSL(member, eventBoard);
//    }
//
//    @Test
//    public void saveTest(){
    }

    @Test
    public void saveTest(){
//        Optional<Member> member = memberRepository.findById(1L);
//        Optional<EventBoard> eventBoard = eventBoardRepository.findById(107L);
//
//        if(!member.isPresent() || !eventBoard.isPresent()) fail("멤버 혹은 이벤트 보드가 존재하지 않음");
//        Integer likeCount =  eventBoard.get().getEventLikeNumber();
//
//        EventLike eventLike = new EventLike(eventBoard.get(), member.get());
//        eventLikeRepository.save(eventLike);
//
//        eventBoard.get().setEventLikeCount(++likeCount);
//        }
        }





}
