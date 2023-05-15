package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventBoardQueryDsl {

    //    이벤트 정보 게시글 전체 리스트 가져오기
    public Page<EventBoard> findAllEventBoardList_QueryDSL(Pageable pageable);

    //     상세보기
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId);

    //    자유게시글 상세 페이지 댓글
    public Optional<EventBoard> findEventBoardAndEventRepliesById_QueryDSL(Long boardId);

    //    게시글 검색
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch);


}
