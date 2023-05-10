package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoardDTO;
import com.app.dodamdodam.entity.purchase.QPurchaseBoard;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;

@RequiredArgsConstructor
public class PurchaseBoardQueryDslImpl implements PurchaseBoardQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<PurchaseBoard> findAllPurchaseBoard(Pageable pageable) {
        boolean hasNext = false;

        List<PurchaseBoard> results = query.select(purchaseBoard)
                .from(purchaseBoard)
                .join(purchaseBoard.member)
                .join(purchaseBoard.purchaseFiles)
                .fetchJoin()
                .limit(10)
                .fetch();

        if(results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize()); //한개 더 가져왔으니 더 가져온 데이터 삭제
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

    @Override
    public Optional<PurchaseBoard> findPurchaseBoardById(Long id) {
        return Optional.ofNullable(
                query.select(QPurchaseBoard.purchaseBoard)
                        .from(QPurchaseBoard.purchaseBoard)
                        .join(QPurchaseBoard.purchaseBoard.product)
                        .join(QPurchaseBoard.purchaseBoard.member)
                        .join(QPurchaseBoard.purchaseBoard.purchaseFiles)
                        .fetchJoin()
                        .where(QPurchaseBoard.purchaseBoard.id.eq(id))
                        .fetchOne()
        );
    }

    @Override
    public Page<PurchaseBoard> findPurchaseBoardListByMemberId(Pageable pageable, Long memberId) {
        List<PurchaseBoard> purchaseBoards = query.select(purchaseBoard).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).orderBy(purchaseBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(purchaseBoard.count()).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(purchaseBoards, pageable, count);
    }
}
