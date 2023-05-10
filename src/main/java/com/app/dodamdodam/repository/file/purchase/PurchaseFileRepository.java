package com.app.dodamdodam.repository.file.purchase;

import com.app.dodamdodam.entity.purchase.PurchaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseFileRepository extends JpaRepository<PurchaseFile, Long>, PurchaseFileQueryDsl {
}
