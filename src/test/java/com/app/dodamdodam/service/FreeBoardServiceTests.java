package com.app.dodamdodam.service;

import com.app.dodamdodam.domain.FreeReplyDTO;
import com.app.dodamdodam.service.board.freeBoard.FreeBoardService;
import com.app.dodamdodam.service.board.freeBoard.freeReply.FreeReplyService;
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
public class FreeBoardServiceTests {
    @Autowired
    private FreeBoardService freeBoardService;
    @Autowired
    private FreeReplyService freeReplyService;

    @Test
    public void findRepliesTest(){
        freeReplyService.getFreeRepliesByBoardId(PageRequest.of(0,5), 204L).stream().map(FreeReplyDTO::toString).forEach(log::info);
    }


}
