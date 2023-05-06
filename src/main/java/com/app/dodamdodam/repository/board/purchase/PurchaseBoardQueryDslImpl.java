package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.QPurchaseBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;

public class PurchaseBoardQueryDslImpl implements PurchaseBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<PurchaseBoard> findPurchaseBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(purchaseBoard).from(purchaseBoard).where(purchaseBoard.member.id.eq(memberId)).orderBy(purchaseBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
