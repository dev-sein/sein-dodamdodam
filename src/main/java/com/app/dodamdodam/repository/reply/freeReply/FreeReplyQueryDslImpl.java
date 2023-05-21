package com.app.dodamdodam.repository.reply.freeReply;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.FreeReply;
import com.app.dodamdodam.entity.free.QFreeReply;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.free.QFreeReply.freeReply;

public class FreeReplyQueryDslImpl implements FreeReplyQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    //    boardId로 그 board에 달린 댓글 가져오기
    @Override
    public Page<FreeReply> findFreeRepliesByBoardId_QueryDSL(Pageable pageable, Long boardId) {
        List<FreeReply> freeReplies = query.select(freeReply).from(freeReply)
                .join(freeReply.member).fetchJoin()
                .where(freeReply.freeBoard.id.eq(boardId))
                .orderBy(freeReply.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeReply.count()).from(freeReply).where(freeReply.freeBoard.id.eq(boardId)).fetchOne();

        return new PageImpl<>(freeReplies, pageable, count);
    }
}