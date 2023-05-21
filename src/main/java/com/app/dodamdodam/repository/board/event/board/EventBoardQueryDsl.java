package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.search.board.AdminEventBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventBoardQueryDsl {

    //    목록 페이징(최신순)
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable);

    //     상세보기
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId);

    //    게시글 검색
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch);

    // 현재 시퀀스 가져오기
    public EventBoard getCurrentSequence_QueryDsl();

    //  어드민 자유게시판 검색
    public Page<EventBoard> findAdmindEventBoardWithPaging_QueryDSL(AdminEventBoardSearch adminEventBoardSearch, Pageable pageable);



}
