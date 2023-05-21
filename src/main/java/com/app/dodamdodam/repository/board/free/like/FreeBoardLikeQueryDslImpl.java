package com.app.dodamdodam.repository.board.free.like;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.QFreeLike;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.board.AdminFreeBoardSearch;
import com.app.dodamdodam.type.CategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.free.QFreeLike.freeLike;

public class FreeBoardLikeQueryDslImpl implements FreeBoardLikeQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    //    boardId, memberId 받아와서 좋아요 취소하기
    @Override
    public void deleteByBoardIdAndMemberId_QueryDSL(Long boardId, Long memberId) {
        query.delete(freeLike).where(freeLike.freeBoard.id.eq(boardId).and(freeLike.memberId.eq(memberId))).execute();
    }

    //    boardId, memberId 받아와서 좋아요 누른 게시글인지 확인(false면 이 게시글에 유저가 좋아요 안누름, true면 누름)
    @Override
    public boolean checkLikeByBoardIdAndMemberId_QueryDSL(Long boardId, Long memberId) {
        Long count = query.select(freeLike.id.count()).from(freeLike).where(freeLike.freeBoard.id.eq(boardId).and(freeLike.memberId.eq(memberId))).fetchOne();
        if (count == 0){
            return false;
        } else {
            return true;
        }
    }
}
