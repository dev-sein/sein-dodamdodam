package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.repository.point.PointRepository;
import com.app.dodamdodam.search.point.AdminPointSearch;
import com.app.dodamdodam.type.BannerType;
import com.app.dodamdodam.type.CategoryType;
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


    /*포인트*/
    @Test //포인트 페이징 조회
    public void findPointWithSearchTest(){
        AdminPointSearch pointSearch = new AdminPointSearch();
        pointSearch.setPointAmount(100000);

        Page<Point> pointPage = pointRepository.findPointWithSearch(pointSearch, PageRequest.of(1,3));
        pointPage.forEach(point -> log.info((point.getPointAmount()+"")));
    }


    @Test //멤버 페이징 조회
    public void findAllMemberTest(){
        Page<Member> memberPage = memberRepository.findAll(PageRequest.of(0, 10));
        memberPage.getContent().stream().map(member -> member.toString()).forEach(log::info);
    }

    @Test //멤버 삭제 (멤버 상태 변경으로 바꿔야 함)
    public void deleteByMemberId(){
        memberRepository.findById(150L).ifPresent(member -> memberRepository.delete(member));
    }

    @Test //멤버 상세 조회(뱃지 조인해야 함)
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

    /*메인*/
    @Test //자유게시판 최신글 3개 글 제목 불러오기
    public void findLasterBoardTest(){
        Pageable pageable = PageRequest.of(0, 3);
        freeBoardRepository.findAllFreeBoardList(pageable).stream().map(FreeBoard::toString).forEach(log::info);

    }

//    @Test //판매게시판 최신글 3개 글 제목 불러오기


}
