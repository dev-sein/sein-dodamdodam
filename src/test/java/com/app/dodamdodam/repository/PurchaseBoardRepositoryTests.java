package com.app.dodamdodam.repository;

import com.app.dodamdodam.entity.purchase.Product;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.repository.board.purchase.PurchaseBoardRepository;
import com.app.dodamdodam.repository.purchase.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class PurchaseBoardRepositoryTests {
    @Autowired
    private PurchaseBoardRepository purchaseBoardRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void saveTest(){
//        Product product = new Product("");
//        PurchaseBoard purchaseBoard = new PurchaseBoard("");

    }


}
