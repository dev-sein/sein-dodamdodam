package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.type.CategoryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;

public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

//    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(freeBoard).from(freeBoard).where(freeBoard.member.id.eq(memberId)).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

//    자유 게시글 전체 리스트 가져오기
    @Override
    public List<FreeBoard> findAllFreeBoardList(Pageable pageable) {
        return query.select(freeBoard).from(freeBoard).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

//    자유 게시글 전체 리스트 Category에 따라 분류해서 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByCategoryType(Pageable pageable, CategoryType categoryType) {
        return query.select(freeBoard).from(freeBoard).where(freeBoard.freeCategory.eq(categoryType)).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }

//    내가 작성한 자유 게시글 리스트 Category에 따라 분류해서 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByCategoryTypeAndMemberId(Pageable pageable, CategoryType categoryType, Long memberId) {
        return query.select(freeBoard).from(freeBoard).where(freeBoard.freeCategory.eq(categoryType).and(freeBoard.member.id.eq(memberId))).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
