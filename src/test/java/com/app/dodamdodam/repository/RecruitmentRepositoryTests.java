package com.app.dodamdodam.repository;

import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.recruitment.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class RecruitmentRepositoryTests {
    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findMemberTest(){
        memberRepository.findById(30L).ifPresent(member -> log.info(member.toString()));
    }
}
