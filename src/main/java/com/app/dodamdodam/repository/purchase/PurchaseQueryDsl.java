package com.app.dodamdodam.repository.purchase;


import com.app.dodamdodam.entity.purchase.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseQueryDsl {

//    상품별 구매 수 조회
    public Long findPurchaseCountByProduct_QueryDSL(Long productId);


}