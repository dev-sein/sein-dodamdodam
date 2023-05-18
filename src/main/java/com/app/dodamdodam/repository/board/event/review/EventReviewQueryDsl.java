package com.app.dodamdodam.repository.board.event.review;

import com.app.dodamdodam.entity.event.EventReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventReviewQueryDsl {
    /*전체 조회 (페이징)*/
    public Slice<EventReview> findAllByEventWithPaging_QueryDsl(Long eventBoardId, Pageable pageable);

    /* 댓글 개수*/
    public Long getReviewCount_QueryDSL(Long eventBoardId);

    /* 댓글 삭제*/
    public void deleteByEventBoardId(Long eventBoardId);
 }
