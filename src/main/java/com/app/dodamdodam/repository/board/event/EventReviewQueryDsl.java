package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventReviewQueryDsl {
    //    댓글 무한스크롤
    public Slice<EventReview> findAllEventReplyByBoardId(Long boardId, Pageable pageable);
}
