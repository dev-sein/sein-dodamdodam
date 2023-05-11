package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseReview;
import com.app.dodamdodam.search.board.AdminPurchaseBoardSearch;
import org.springframework.data.domain.Page;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface PurchaseBoardQueryDsl {

////    무한스크롤 10개씩
//    Slice<PurchaseBoard> findAllPurchaseBoard_QueryDSL(Pageable pageable);

//    판매게시글 각각
    public Optional<PurchaseBoard> findPurchaseBoardById_QueryDSL(Long boardId);


//    세션에 담긴 id 값 받아와서 내가 작성한 판매 게시글 리스트 가져오기
    public Page<PurchaseBoard> findPurchaseBoardListByMemberId(Pageable pageable, Long memberId);

//    게시글 검색
    public Slice<PurchaseBoard> findAllWithSearch_QueryDSL(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable);

//  관리자 판매 게시판 검색
    public Page<PurchaseBoard> findadminPurchaseSearchWithPaging_QueryDSL(AdminPurchaseBoardSearch adminPurchaseBoardSearch, Pageable pageable);


}
