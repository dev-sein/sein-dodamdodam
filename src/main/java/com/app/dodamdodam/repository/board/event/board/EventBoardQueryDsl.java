package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventBoardQueryDsl {
//    목록 페이징처리
    public Page<EventBoard> findAllEventBoardWithPaging_QueryDSL(Pageable pageable);

    //    판매게시글 상세보기
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId);

    //    세션에 담긴 id 값 받아와서 내가 작성한 판매 게시글 리스트 가져오기
    public Page<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId);

    //    게시글 검색
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch);


}
