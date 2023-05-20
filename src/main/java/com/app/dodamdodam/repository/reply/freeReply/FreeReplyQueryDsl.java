package com.app.dodamdodam.repository.reply.freeReply;

import com.app.dodamdodam.entity.free.FreeReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreeReplyQueryDsl {
    //    boardId로 댓글 리스트 가져오기
    public Page<FreeReply> findFreeRepliesByBoardId(Pageable pageable, Long boardId);
}