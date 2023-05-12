package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import jdk.jfr.Event;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventBoardQueryDsl {


    //    판매게시글 각각
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long boardId);

    //    세션에 담긴 id 값 받아와서 내가 작성한 판매 게시글 리스트 가져오기
    public Page<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId);

    //    게시글 검색
    public Slice<EventBoard> findAllWithSearch_QueryDSL(EventBoardSearch eventBoardSearch, Pageable pageable);



}
