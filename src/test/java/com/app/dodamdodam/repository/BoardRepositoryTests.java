package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.point.Point;
import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import com.app.dodamdodam.repository.board.free.FreeBoardRepository;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.board.recruitment.RecruitmentBoardRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import com.app.dodamdodam.type.CategoryType;
import com.app.dodamdodam.type.PointStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Rollback(false)
@Transactional
@Slf4j
public class BoardRepositoryTests {

    @Autowired
    private RecruitmentBoardRepository recruitmentBoardRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    ArrayList<CategoryType> categoryTypes = new ArrayList<CategoryType>(Arrays.asList(CategoryType.ALL, CategoryType.CULTURE, CategoryType.DAILY, CategoryType.EVENT, CategoryType.PURCHASE, CategoryType.RECRUITMENT));

    /*모집 게시글 등록*/
    @Test
    public void saveTest(){
        for (int i=1; i<=100; i++){
            RecruitmentBoard recruitmentBoard = new RecruitmentBoard("모집 게시글 제목" + i, LocalDate.now(),10 + i, "www.naver.com", "1234", "경기도 성남시", "분당구 수내동");
            memberRepository.findById(2L).ifPresent(member -> recruitmentBoard.setMember(member));
            recruitmentBoardRepository.save(recruitmentBoard);
        }
    }

    /*자유 게시글 등록*/
    @Test
    public void saveTest2(){
        FreeBoard freeBoard = new FreeBoard("자유 게시글 제목","자유 게시글 내용" , categoryTypes.get(1));
        memberRepository.findById(5L).ifPresent(member -> freeBoard.setMember(member));
        freeBoardRepository.save(freeBoard);
//        for (int i=1; i<=100; i++){
//            FreeBoard freeBoard = new FreeBoard("자유 게시글 제목" + i,"자유 게시글 내용" + i, categoryTypes.get(i % 6));
//            memberRepository.findById(2L).ifPresent(member -> freeBoard.setMember(member));
//            freeBoardRepository.save(freeBoard);
//        }
    }

    /*판매 게시글 등록*/
    @Test
    public void saveTest3(){
        for (int i=1; i<=100; i++){
            Product product = new Product("상품" + i, 1000 * i, (long)i);
            PurchaseBoard purchaseBoard = new PurchaseBoard("판매 게시글 제목" + i, "판매 게시글 내용"+ i);

            memberRepository.findById(2L).ifPresent(member -> {
                purchaseBoard.setMember(member);
                purchaseBoard.setProduct(product);
            });

            purchaseBoardRepository.save(purchaseBoard);
        }
    }

    @Test
    public void findByIdTest(){
        Pageable pageable = PageRequest.of(0,10);
        freeBoardRepository.findFreeBoardListByMemberId(pageable, 5L).stream().map(FreeBoard::toString).forEach(log::info);

    }
}
