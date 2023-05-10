package com.app.dodamdodam.repository.reply.purchaseReview;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseReview;
import com.app.dodamdodam.entity.purchase.QPurchaseReview;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseReview.purchaseReview;

@RequiredArgsConstructor
public class PurchaseReviewQueryDslImpl implements PurchaseReviewQueryDsl {
    private final JPAQueryFactory query;


//    댓글 무한스크롤
    @Override
    public Slice<PurchaseReview> findAllPurchaseReviewByBoardId(Long boardId, Pageable pageable) {
        boolean hasNext = false;

        List<PurchaseReview> purchaseReviews = query.select(purchaseReview)
                .from(purchaseReview)
                .join(purchaseReview.member)
                .fetchJoin()
                .orderBy(purchaseReview.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        if(purchaseReviews.size() > pageable.getPageSize()) {
            hasNext = true;
            purchaseReviews.remove(pageable.getPageSize()); // 한개 더 가져왔으니 더 가져온 데이터 삭제
        }

        return new SliceImpl<>(purchaseReviews, pageable, hasNext);
    }

}
