package com.app.dodamdodam.repository.board.purchase;

import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseBoardRepository extends JpaRepository<PurchaseBoard, Long>, PurchaseBoardQueryDsl {
}
