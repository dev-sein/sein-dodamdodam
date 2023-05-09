package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.purchase.Purchase;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentFile;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.point.PointSearch;
import com.app.dodamdodam.type.BannerType;
import com.app.dodamdodam.type.CategoryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

    @Autowired
    public BannerRepository bannerRepository;

    @Autowired
    public PurchaseBoardRepository purchaseBoardRepository;

    @Autowired
    public FreeBoardRepository freeBoardRepository;


    ArrayList<CategoryType> categoryTypes = new ArrayList<CategoryType>(Arrays.asList(CategoryType.ALL, CategoryType.CULTURE, CategoryType.DAILY, CategoryType.EVENT, CategoryType.PURCHASE, CategoryType.RECRUITMENT));


    @Test //포인트 상세
    public void findPointAllWithSearchTest(){
        PointSearch pointSearch = new PointSearch();
        pointSearch.setPointAmount(100000);

        Page<Point> pointPage = pointRepository.findAllPointWithSearch(pointSearch, PageRequest.of(1,3));
        pointPage.forEach(point -> log.info((point.getPointAmount()+"")));

    }

/*
    @Test //포인트 페이징 조회
    public void findAllPointWithSearchAndPageTest(){
        PointSearch pointSearch = new PointSearch();
        pointSearch.setPointAmount(10000);
        Page<Point> pointPage = pointRepository.findAllPointWithSearch(pointSearch, PageRequest.of(1, 2));
//        pointPage.stream().map(point -> point.toString()).forEach(log::info);
        log.info("=========="+pointPage.getContent()); //==251만 가져옴, 351도 10000 포인트임
        //size가 2일 때 251 반환, size가 1일때 351 반환으로 총 결과값이 두개이지만 하나씩만 반환됨.
        log.info("=========="+pointPage.getTotalElements()); //==101 반환 (point 전체 행의 수)
    }
*/

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

//    @Test //모집글 상세보기 수정, 파일 넣어서 출력 해보기
//    public void recruitmentFindById(){
//        recruitmentBoardRepository.findById(450L)
//                .ifPresent(recruitmentBoard -> recruitmentBoard.getRecruitments()).forEach(log::info));
//    }

    @Test //배너 삭제
    public void deleteBannerTest(){
        bannerRepository.findById(754l).ifPresent(bannerApply -> bannerRepository.delete(bannerApply));

    }

    @Test //배너 신청
    public void bannerSaveTest(){
        for(int i=0; i<10; i++){
            BannerApply bannerApply = new BannerApply(LocalDate.now(),1);
            memberRepository.findById(51L).ifPresent(member -> bannerApply.setMember(member));
            bannerRepository.save(bannerApply);
        }
    }

    @Test //배너 상세 조회
    public void findBannerById(){
        bannerRepository.findById(755L).ifPresent(bannerApply -> log.info(bannerApply.toString()));
    }

    @Test //배너 목록
    public void bannerFindByAllWithPaging(){
        Page<BannerApply> bannerApplyPage = bannerRepository.findAll(PageRequest.of(0,5));
        bannerApplyPage.forEach(bannerApply -> log.info(bannerApply.getMember()+""));
    }

    @Test //판매 게시글 조회 수정 조회
    public void purchaseFindAllWithPaging(){
        Page<PurchaseBoard> purchasePage = purchaseBoardRepository.findAll(PageRequest.of(0,5));
        purchasePage.forEach(purchaseBoard -> log.info(purchaseBoard.getId()+""));
    }

    @Test //판매 게시글 삭제
    public void purchaseBoardDelete(){
        purchaseBoardRepository.findById(753L).ifPresent(purchaseBoard -> purchaseBoardRepository.delete(purchaseBoard));
    }

    @Test //자유 게시글 조회
    public void freeeFindAllWithPaging(){
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAll(PageRequest.of(0,5));
        freeBoardPage.forEach(freeBoard -> log.info(freeBoard.getBoardContent()+""));
    }

    @Test //자유 게시글 삭제
    public void freeBoardDelete(){
        freeBoardRepository.findById(653L).ifPresent(freeBoard -> freeBoardRepository.delete(freeBoard));
    }

    @Test //배너 신청 수락 및 거절
    public void setBannerStatus(){
        bannerRepository.findById(764L).ifPresent(bannerApply -> bannerApply.setBannerStatus(BannerType.REJECT));
    }

    @Test //자유게시글 상세
    public void findFreeBoardById(){
        freeBoardRepository.findById(555L).ifPresent(freeBoard -> log.info(freeBoard.toString()));
    }


}
