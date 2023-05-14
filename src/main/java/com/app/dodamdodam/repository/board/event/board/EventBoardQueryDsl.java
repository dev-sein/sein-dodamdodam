package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface EventBoardQueryDsl {

    // 페이징 처리를 위한 communityPostDTO 전체 가져오기 + 검색어 추출 가능
    public Page<EventBoardDTO> findAll(Pageable pageable, Criteria criteria);

//    목록 페이징처리(최신순)
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable);
    //     상세보기
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId);

    //    세션에 담긴 id 값 받아와서 내가 작성한 판매 게시글 리스트 가져오기
    public Page<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId);

    // 게시글 삭제
    public void deleteByPost(EventBoard eventBoard);

    //    게시글 검색
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch);


}
