package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.entity.free.FreeBoard;
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

public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public Page<FreeBoard> findFreeBoardListByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.member.id.eq(memberId))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard)
                .where(freeBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 개수 가져오기
    @Override
    public Long findFreeBoardListCountByMemberId_QueryDSL(Long memberId) {
        return query.select(freeBoard.count()).from(freeBoard)
                .where(freeBoard.member.id.eq(memberId))
                .orderBy(freeBoard.id.desc())
                .fetchOne();
    }


    //    자유 게시글 전체 리스트 가져오기
    @Override
    public Page<FreeBoard> findAllFreeBoardList_QueryDSL(Pageable pageable) {
        List<FreeBoard> freeBoards = query.selectFrom(freeBoard)
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .join(freeBoard.member).fetchJoin()
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.id.count()).from(freeBoard)
                .fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }

    //    자유 게시글 전체 리스트 Category에 따라 분류해서 가져오기
    @Override
    public Page<FreeBoard> findFreeBoardListByCategoryType_QueryDSL(Pageable pageable, CategoryType categoryType) {
        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.freeCategory.eq(categoryType))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard).where(freeBoard.freeCategory.eq(categoryType)).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }


    //    내가 작성한 자유 게시글 리스트 Category에 따라 분류해서 가져오기
    @Override
    public Page<FreeBoard> findFreeBoardListByCategoryTypeAndMemberId_QueryDSL(Pageable pageable, CategoryType categoryType, Long memberId) {
        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.member.id.eq(memberId).and(freeBoard.freeCategory.eq(categoryType)))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard).where(freeBoard.member.id.eq(memberId).and(freeBoard.freeCategory.eq(categoryType))).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }

    @Override //관리자 자유게시판 검색
    public Page<FreeBoard> findAdmindFreeBoardWithPaging_QueryDSL(AdminFreeBoardSearch freeBoardSearch, Pageable pageable) {
        BooleanExpression freeCategoryEq = freeBoardSearch.getFreeCategory() == null ? null : freeBoard.freeCategory.eq(freeBoardSearch.getFreeCategory());
        BooleanExpression freeTitleEq = freeBoardSearch.getBoardTitle() == null ? null : freeBoard.boardTitle.eq(freeBoardSearch.getBoardTitle());
        BooleanExpression memberNameEq = freeBoardSearch.getMemberName() == null ? null : freeBoard.member.memberName.eq(freeBoardSearch.getMemberName());

        List<FreeBoard> adminFreeBoards = query.select(freeBoard)
                .from(freeBoard)
                .where(freeCategoryEq, freeTitleEq, memberNameEq)
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard).fetchOne();

        return new PageImpl<>(adminFreeBoards, pageable, count);
    }

    // 자유게시판 검색
    @Override
    public Page<FreeBoard> findFreeBoardBySearchWithPaging_QueryDSL(FreeBoardSearch freeBoardSearch, CategoryType categoryType, Pageable pageable) {
        BooleanExpression writerNameEq = freeBoardSearch.getWriterName() == null ? null : freeBoard.member.memberName.contains(freeBoardSearch.getWriterName());
        BooleanExpression freeTitleEq = freeBoardSearch.getBoardTitle() == null ? null : freeBoard.boardTitle.contains(freeBoardSearch.getBoardTitle());
        BooleanExpression freeContentEq = freeBoardSearch.getBoardContent() == null ? null : freeBoard.boardContent.contains(freeBoardSearch.getBoardContent());
        BooleanExpression categoryEq = categoryType == null ? null : freeBoard.freeCategory.eq(categoryType);

        List<FreeBoard> freeBoards = query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(writerNameEq.or(freeTitleEq).or(freeContentEq).and(categoryEq))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(freeBoard.count()).where(writerNameEq.or(freeTitleEq).or(freeContentEq).and(categoryEq)).from(freeBoard).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }

    // 자유게시판 좋아요 Top5 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByLikeCount_QueryDSL() {
        return query.selectFrom(freeBoard).orderBy(freeBoard.likeCount.desc()).limit(5).fetch();
    }

    @Override
    public List<FreeBoard> findRecentFreeBoardList_QueryDSL() {
        return query.selectFrom(freeBoard)
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .orderBy(freeBoard.id.desc())
                .limit(5)
                .fetch();
    }

    //    게시글 상세페이지 board 정보, 작성자 정보, 첨부파일
    @Override
    public Optional<FreeBoard> findFreeBoardAndFreeFilesById_QueryDSL(Long boardId) {
        return Optional.ofNullable(query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.id.eq(boardId))
                .fetchOne());
    }

    //    게시글 상세페이지 board 정보, reply정보
    @Override
    public Optional<FreeBoard> findFreeBoardAndFreeRepliesById_QueryDSL(Long boardId) {
        return Optional.ofNullable(query.select(freeBoard).from(freeBoard)
                .leftJoin(freeBoard.freeReplies).fetchJoin()
                .where(freeBoard.id.eq(boardId))
                .fetchOne());
    }

//    @Override
//    public Page<FreeBoard> findFreeBoardWithPaging_QueryDSL(FreeBoardSearch freeBoardSearch, Pageable pageable) {
//        return null;
//    }

}