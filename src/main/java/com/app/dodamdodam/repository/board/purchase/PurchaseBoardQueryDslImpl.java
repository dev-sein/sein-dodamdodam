package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.search.board.AdminPurchaseBoardSearch;
import com.querydsl.core.Tuple;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.entity.purchase.*;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;
import static com.app.dodamdodam.entity.recruitment.QRecruitmentBoard.recruitmentBoard;


@RequiredArgsConstructor
public class PurchaseBoardQueryDslImpl implements PurchaseBoardQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Slice<PurchaseBoard> findAllPurchaseBoard_QueryDSL(Pageable pageable) {
//        boolean hasNext = false;
//
//        List<PurchaseBoard> results = query.select(purchaseBoard)
//                .from(purchaseBoard)
//                .join(purchaseBoard.member)
//                .join(purchaseBoard.purchaseFiles)
//                .fetchJoin()
//                .limit(10)
//                .fetch();
//
//        if(results.size() > pageable.getPageSize()) {
//            hasNext = true;
//            results.remove(pageable.getPageSize()); //한개 더 가져왔으니 더 가져온 데이터 삭제
//        }
//
//        return new SliceImpl<>(results, pageable, hasNext);
//    }

    @Override
    public Optional<PurchaseBoard> findPurchaseBoardById_QueryDSL(Long boardId) {
        return Optional.ofNullable(
                query.select(purchaseBoard)
                        .from(purchaseBoard)
                        .join(purchaseBoard.product)
                        .fetchJoin()
                        .join(purchaseBoard.member)
                        .fetchJoin()
                        .join(purchaseBoard.purchaseFiles)
                        .fetchJoin()
                        .where(purchaseBoard.id.eq(boardId))
                        .fetchOne()
        );
    }

    @Override
    public Long findPurchaseBoardListCountByMemberId_QueryDSL(Long memberId) {
        return query.select(purchaseBoard.count()).from(purchaseBoard)
                .where(purchaseBoard.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Page<PurchaseBoard> findPurchaseBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard).from(purchaseBoard)
                .join(purchaseBoard.member).fetchJoin()
                .leftJoin(purchaseBoard.purchaseFiles).fetchJoin()
                .where(purchaseBoard.member.id.eq(memberId))
                .orderBy(purchaseBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(purchaseBoards, pageable, count);
    }

    @Override
    public Page<PurchaseBoard> findBoughtPurchaseBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard).from(purchaseBoard)
                .join(purchaseBoard.product).fetchJoin()
                .leftJoin(purchaseBoard.product.purchase).fetchJoin()
                .where(purchaseBoard.product.purchase.member.id.eq(memberId))
                .orderBy(purchaseBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();


        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).where(purchaseBoard.product.purchase.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(purchaseBoards, pageable, count);
    }


    //    무한스크롤
    @Override
    public Slice<PurchaseBoard> findAllWithSearch_QueryDSL(PurchaseBoardSearch purchaseBoardSearch, Pageable pageable){
        BooleanExpression boardTitleContains = purchaseBoardSearch.getBoardTitle() == null ? null : purchaseBoard.boardTitle.contains(purchaseBoardSearch.getBoardTitle());
        BooleanExpression boardContentContains = purchaseBoardSearch.getBoardContent() == null ? null : purchaseBoard.boardContent.contains(purchaseBoardSearch.getBoardContent());
        BooleanExpression productNameContains = purchaseBoardSearch.getProductName() == null ? null : purchaseBoard.product.productName.contains(purchaseBoardSearch.getProductName());

        boolean hasNext = false;

        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard)
                .from(purchaseBoard)
                .join(purchaseBoard.purchaseFiles)
                .fetchJoin()
                .join(purchaseBoard.product)
                .fetchJoin()
                .join(purchaseBoard.member)
                .fetchJoin()
                .where(boardTitleContains, boardContentContains, productNameContains)
                .orderBy(purchaseBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        if(purchaseBoards.size() > pageable.getPageSize()) {
            hasNext = true;
            purchaseBoards.remove(pageable.getPageSize()); // 한개 더 가져왔으니 더 가져온 데이터 삭제
        }

        return new SliceImpl<>(purchaseBoards, pageable, hasNext);

    }

    @Override //관리자 판매 게시글
    public Page<PurchaseBoard> findadminPurchaseSearchWithPaging_QueryDSL(AdminPurchaseBoardSearch adminPurchaseBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleEq = adminPurchaseBoardSearch.getBoardTitle() == null ? null : purchaseBoard.boardTitle.eq(adminPurchaseBoardSearch.getBoardTitle());
        BooleanExpression memberNameEq = adminPurchaseBoardSearch.getMemberName() == null ? null : purchaseBoard.member.memberName.eq(adminPurchaseBoardSearch.getMemberName());
        BooleanExpression productCountEq = adminPurchaseBoardSearch.getProductCount() == null ? null : purchaseBoard.product.productCount.eq(adminPurchaseBoardSearch.getProductCount());
        BooleanExpression productPriceEq = adminPurchaseBoardSearch.getProductPrice() == null ? null : purchaseBoard.product.productPrice.eq(adminPurchaseBoardSearch.getProductPrice());

        List<PurchaseBoard> adminPurchaseBoards = query.select(purchaseBoard)
                .from(purchaseBoard)
                .where(boardTitleEq, memberNameEq, productCountEq, productPriceEq)
                .orderBy(purchaseBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).fetchOne();

        return new PageImpl<>(adminPurchaseBoards, pageable, count);
    }

    //관리자 목록 페이지
    @Override
    public Page<PurchaseBoard> findAllWithPaging(Pageable pageable) {
        List<PurchaseBoard> foundPurchase = query.select(purchaseBoard)
                .from(purchaseBoard)
                .orderBy(purchaseBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(purchaseBoard.count())
                .from(purchaseBoard)
                .fetchOne();

        return new PageImpl<>(foundPurchase, pageable, count);

    }

}
