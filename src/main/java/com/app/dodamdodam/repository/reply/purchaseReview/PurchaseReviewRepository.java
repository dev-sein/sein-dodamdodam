package com.app.dodamdodam.repository.reply.purchaseReview;

import com.app.dodamdodam.entity.purchase.PurchaseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReviewRepository extends JpaRepository<PurchaseReview, Long>, PurchaseReviewQueryDsl {
}
