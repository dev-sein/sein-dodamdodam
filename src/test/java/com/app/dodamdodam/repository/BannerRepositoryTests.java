package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Rollback(false)
@Transactional
@Slf4j
public class BannerRepositoryTests {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private MemberRepository memberRepository;

    /*배너 신청*/
    @Test
    public void saveTest(){
        BannerApply bannerApply = new BannerApply(LocalDate.now(),1);
        memberRepository.findById(2L).ifPresent(member -> bannerApply.setMember(member));

        bannerRepository.save(bannerApply);
    }
}
