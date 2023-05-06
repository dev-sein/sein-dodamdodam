package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoardDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface PurchaseBoardQueryDsl {
//    무한스크롤 10개씩
    Slice<PurchaseBoard> findAllPurchaseBoard(Pageable pageable);
//    판매게시글 각각
    Optional<PurchaseBoard> findPurchaseBoardById(Long id);
}
