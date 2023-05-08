package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;

public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;


    @Override
    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(freeBoard).from(freeBoard).where(freeBoard.member.id.eq(memberId)).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

}