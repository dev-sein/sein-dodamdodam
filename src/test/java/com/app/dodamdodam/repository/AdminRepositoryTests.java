package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentFile;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class AdminRepositoryTests {

    @Autowired
    public PointRepository pointRepository;

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public RecruitmentBoardRepository recruitmentBoardRepository;

    @Test //포인트 페이징 조회
    public void findAllTest(){
        Page<Point> pointPage = pointRepository.findAll(PageRequest.of(0, 10));
        pointPage.getContent().stream().map(point -> point.toString()).forEach(log::info);
    }

    @Test //멤버 페이징 조회
    public void findAllMemberTest(){
        Page<Member> memberPage = memberRepository.findAll(PageRequest.of(0, 10));
        memberPage.getContent().stream().map(member -> member.toString()).forEach(log::info);
    }

    @Test //멤버 삭제
    public void deleteByMemberId(){
        memberRepository.findById(150L).ifPresent(member -> memberRepository.delete(member));
    }

    @Test //멤버 상세 조회
    public void findByIdTest(){
        memberRepository.findById(51L).ifPresent(member -> log.info(member.toString()));
    }

    @Test //모집글 조회, recruitments 출력 수정해야함
    public void findAllrecruitmentTest(){
        Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardRepository.findAll(PageRequest.of(0,10));
        recruitmentBoardPage.getContent().stream().map(recruitmentBoard -> recruitmentBoard.toString()).forEach(log::info);
    }

    @Test //모집글 삭제
    public void recuirmentDeleteTest(){
        recruitmentBoardRepository.findById(451L).ifPresent(recruitmentBoard -> recruitmentBoardRepository.delete(recruitmentBoard));
    }

    @Test //모집글 상세보기 수정, 파일 넣어서 출력 해보기
    public void recruitmentFindById(){
        recruitmentBoardRepository.findById(450L).ifPresent(recruitmentBoard -> log.info(recruitmentBoard.toString()));
    }

    @Test //파일
    public void recruitmentFileSaveTest(){
    }

    @Test //이벤트 조회
    public void eventBoardfindAllTest(){

    }

}
