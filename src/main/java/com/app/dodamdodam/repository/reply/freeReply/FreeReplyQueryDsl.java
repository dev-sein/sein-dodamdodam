package com.app.dodamdodam.repository.reply.freeReply;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FreeReplyQueryDsl {
    //    boardId로 댓글 리스트 가져오기
    public Page<FreeReply> findFreeRepliesByBoardId(Pageable pageable, Long boardId);
}