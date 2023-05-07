package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
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

//    @Test //모집글 조회



}
