package com.app.dodamdodam.repository.reply.eventReply;

import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.entity.purchase.PurchaseReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventReplyQueryDsl {
    //    댓글 무한스크롤
    public Slice<EventReply> findAllEventReplyByBoardId(Long boardId, Pageable pageable);
}
