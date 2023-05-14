package com.app.dodamdodam.service;

import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.purchase.PurchaseBoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class MyPageServiceTests {
    @Autowired
    private FreeBoardService freeBoardService;
    @Autowired
    private PurchaseBoardService purchaseBoardService;


    @Test
    public void findFreeBoardListByMemberIdTest(){
        Pageable pageable = PageRequest.of(0,6);
        log.info(freeBoardService.getFreeBoardsByMemberId(pageable, 5L).toString());
    }

    @Test
    public void findPurchaseBoardListByMemberIdTest(){
        Pageable pageable = PageRequest.of(0,6);
        log.info(purchaseBoardService.getPurchaseBoardListByMemberId(pageable, 5L).toString());
    }

}
