package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.search.banner.AdminBannerSearch;
import com.app.dodamdodam.type.BannerType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        memberRepository.findById(201L).ifPresent(member -> bannerApply.setMember(member));
        bannerRepository.save(bannerApply);
    }

    //관리자 배너 검색
    @Test
    public void findAdminBannerApplyWithPaging_QueryDSL(){
        AdminBannerSearch adminBannerSearch = new AdminBannerSearch();
    //    adminBannerSearch.setBannerStatus(BannerType.REJECT);
        adminBannerSearch.setMemberName("테스트3");
        Page<BannerApply> bannerApplyPage = bannerRepository.findAdminBannerApplyWithPaging_QueryDSL(adminBannerSearch, PageRequest.of(0, 10));
        log.info(""+bannerApplyPage.getContent());
    }

}
