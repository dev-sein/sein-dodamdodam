package com.app.dodamdodam.repository.purchase;

import com.app.dodamdodam.entity.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
