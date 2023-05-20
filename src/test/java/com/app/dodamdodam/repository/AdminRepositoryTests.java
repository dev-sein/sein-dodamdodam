package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.search.board.AdminPurchaseBoardSearch;
import com.app.dodamdodam.search.board.AdminRecruitmentSearch;
import com.app.dodamdodam.search.member.AdminMemberSearch;
import com.app.dodamdodam.type.BannerType;
import com.app.dodamdodam.type.CategoryType;
import com.app.dodamdodam.type.MemberStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    public EventBoardRepository eventBoardRepository;

    ArrayList<CategoryType> categoryTypes = new ArrayList<CategoryType>(Arrays.asList(CategoryType.ALL, CategoryType.CULTURE, CategoryType.DAILY, CategoryType.EVENT, CategoryType.PURCHASE, CategoryType.RECRUITMENT));

//    @Test
//    public void eventBoardsaveTest(){
//        EventBoard eventBoard = new EventBoard("모집 게시글 제목", LocalDateTime.now(), )
//    }

    /*모집 게시글 등록*/
    @Test
    public void saveTest1(){
//        for (int i=1; i<=100; i++){
//            RecruitmentBoard recruitmentBoard = new RecruitmentBoard("모집 게시글 제목" + i, LocalDate.now(),10 + i, "www.naver.com", "1234", "경기도 성남시", "분당구 수내동");
//            memberRepository.findById(2L).ifPresent(member -> recruitmentBoard.setMember(member));
//            recruitmentBoardRepository.save(recruitmentBoard);
//        }
        RecruitmentBoard recruitmentBoard = new RecruitmentBoard("모집 게시글 제목", LocalDate.now(),10, "https://open.kakao.com/o/ggmF0Jkf", "1234", "경기도 성남시 분당구 수내동", "탄천앞");
        memberRepository.findById(201L).ifPresent(member -> recruitmentBoard.setMember(member));
//        recruitmentBoard.addRecruitment();
        recruitmentBoardRepository.save(recruitmentBoard);
    }

    /*목록 조회*/
    @Test //멤버 페이징 조회
    public void findAllMemberTest(){
        Page<Member> memberPage = memberRepository.findAll(PageRequest.of(0, 10));
        memberPage.getContent().stream().map(member -> member.toString()).forEach(log::info);
    }

    @Test //모집글 조회
    public void findAllrecruitmentTest(){
        Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardRepository.findAll(PageRequest.of(0,5));
        recruitmentBoardPage.getContent().stream().map(recruitmentBoard -> recruitmentBoard.toString()).forEach(log::info);
    }

    @Test //판매 게시글 조회 수정 조회
    public void purchaseFindAllWithPaging(){
        Page<PurchaseBoard> purchasePage = purchaseBoardRepository.findAll(PageRequest.of(0,5));
        purchasePage.getContent().stream().map(purchaseBoard -> purchaseBoard.toString()).forEach(log::info);
    }

    @Test //자유 게시글 조회
    public void freeeFindAllWithPaging(){
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAll(PageRequest.of(0,5));
        freeBoardPage.getContent().stream().map(freeBoard -> freeBoard.toString()).forEach(log::info);
    }

    @Test //배너 목록
    public void bannerFindByAllWithPaging(){
        Page<BannerApply> bannerApplyPage = bannerRepository.findAll(PageRequest.of(0,5));
        bannerApplyPage.getContent().stream().map(bannerApply -> bannerApply.toString()).forEach(log::info);
    }

    @Test //포인트 목록
    public void pointFindAllWithPaging(){
        Page<Point> pointPage = pointRepository.findAll(PageRequest.of(1,10));
        pointPage.getContent().stream().map(point -> point.toString()).forEach(log::info);
    }

    /*상세 조회*/
    @Test //자유게시글 상세
    public void findFreeBoardById(){
        freeBoardRepository.findById(401L).stream().map(FreeBoard::getFreeFiles).forEach(freeFiles -> log.info(freeFiles.toString()));
        freeBoardRepository.findById(401L).ifPresent(freeBoard -> log.info(freeBoard.toString())); }

    @Test //판매게시글 상세
    public void findPurchaseBoardById(){
        purchaseBoardRepository.findById(1794L).ifPresent(purchaseBoard -> log.info(purchaseBoard.toString()));
    }

    @Test
    public void adminFindPurchaseBoardById_QueryDSLTest(){
        purchaseBoardRepository.findPurchaseBoardById_QueryDSL(2010L)
                .ifPresent(purchaseBoard -> log.info(purchaseBoard.toString()));
    }

    @Test //배너 상세 조회
    public void findBannerById(){ bannerRepository.findById(1693L).ifPresent(bannerApply -> log.info(bannerApply.toString())); }

    @Test //멤버 상세 조회(뱃지 조인해야 함)
    public void findByIdTest(){
        memberRepository.findById(1441L).ifPresent(member -> log.info(member.toString()));
    }


    /*메인*/
    @Test //자유게시판 최신글 3개 글 제목 불러오기
    public void findLasterBoardTest(){
        Pageable pageable = PageRequest.of(0, 3);
        freeBoardRepository.findAllFreeBoardList_QueryDSL(pageable).stream().map(FreeBoard::toString).forEach(log::info);

    }

    /*삭제 및 상태 변경*/
    @Test //멤버 삭제 (멤버 상태 변경)
    public void deleteByMemberId(){ memberRepository.findById(880L).ifPresent(member -> member.setMemberStatus(MemberStatus.WITHDRAWAL));}

    @Test //판매 게시글 삭제
    public void purchaseBoardDelete(){ purchaseBoardRepository.findById(753L).ifPresent(purchaseBoard -> purchaseBoardRepository.delete(purchaseBoard)); }

    @Test //자유 게시글 삭제
    public void freeBoardDelete(){ freeBoardRepository.findById(653L).ifPresent(freeBoard -> freeBoardRepository.delete(freeBoard)); }

    @Test //모집글 삭제
    public void recuirmentDeleteTest(){ recruitmentBoardRepository.findById(1045L).ifPresent(recruitmentBoard -> recruitmentBoardRepository.delete(recruitmentBoard)); }

    @Test //배너 삭제
    public void deleteBannerTest(){ bannerRepository.findById(754l).ifPresent(bannerApply -> bannerRepository.delete(bannerApply)); }

    @Test //배너 신청 수락 및 거절
    public void setBannerStatus(){ bannerRepository.findById(764L).ifPresent(bannerApply -> bannerApply.setBannerStatus(BannerType.REJECT)); }

    /*검색*/
    @Test //자유 게시판 검색
    public void findAdmindFreeBoardWithPaging_QueryDSL_Test(){
        AdminFreeBoardSearch adminFreeBoardSearch = new AdminFreeBoardSearch();
        adminFreeBoardSearch.setBoardTitle("자유 게시글 제목47");
//        adminFreeBoardSearch.setMemberName("테스트");
        Page<FreeBoard> freeBoardPage = freeBoardRepository.findAdmindFreeBoardWithPaging_QueryDSL(adminFreeBoardSearch, PageRequest.of(0, 10));
        log.info("============"+freeBoardPage.getContent());
    }

    @Test //모집 게시판 검색
    public void findAdminRecruitmentBoardWithPaging_QueryDSL_Test(){
        AdminRecruitmentSearch adminRecruitmentSearch = new AdminRecruitmentSearch();
        adminRecruitmentSearch.setBoardTitle("자유 게시글 제목47");
//        adminFreeBoardSearch.setMemberName("테스트");
        adminRecruitmentSearch.setBoardTitle("");
        Page<RecruitmentBoard> recruitmentBoardPage = recruitmentBoardRepository.findAdminRecruitmentBoardWithPaging_QueryDSL(adminRecruitmentSearch, PageRequest.of(0, 10));
        log.info("============"+recruitmentBoardPage.getContent());
    }


    @Test //판매 게시판 검색
    public void findadminPurchaseSearchWithPaging_QueryDSL_Test(){
        AdminPurchaseBoardSearch adminPurchaseBoardSearch = new AdminPurchaseBoardSearch();
//        adminPurchaseBoardSearch.setBoardTitle("자유 게시글 제목47");
//        adminFreeBoardSearch.setMemberName("테스트");
        adminPurchaseBoardSearch.setBoardTitle("판매 게시글 제목1");
        Page<PurchaseBoard> purchaseBoardAdminPages = purchaseBoardRepository.findadminPurchaseSearchWithPaging_QueryDSL(adminPurchaseBoardSearch, PageRequest.of(0, 10));
        log.info("============"+purchaseBoardAdminPages.getContent());
    }

    @Test //관리자 멤버검색
    public void findAdminMemberWithPaging_QueryDSL_Test(){
        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();
//        adminPurchaseBoardSearch.setBoardTitle("자유 게시글 제목47");
//        adminFreeBoardSearch.setMemberName("테스트");
//        adminMemberSearch.setMemberName("판매 게시글 제목1");
//        adminMemberSearch.setMemberPhone("판매 게시글 제목1");
//        adminMemberSearch.setMemberEmail("판매 게시글 제목1");
        Page<Member> memberAdminPage = memberRepository.findAdminMemberWithPaging_QueryDSL(adminMemberSearch, PageRequest.of(0, 10));
        log.info("============"+memberAdminPage.getContent());
    }


}
