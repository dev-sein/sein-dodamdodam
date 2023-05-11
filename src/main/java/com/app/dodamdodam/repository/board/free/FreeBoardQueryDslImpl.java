package com.app.dodamdodam.repository.board.free;

import com.app.dodamdodam.domain.QFreeBoardFileDTO;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.free.QFreeBoard;
import com.app.dodamdodam.entity.free.QFreeFile;
import com.app.dodamdodam.entity.free.QFreeReply;
import com.app.dodamdodam.entity.member.QMember;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.entity.reply.QReply;
import com.app.dodamdodam.type.CategoryType;
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
    public Page<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId) {
//        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard).where(freeBoard.member.id.eq(memberId)).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.member.id.eq(memberId))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard)
                .where(freeBoard.member.id.eq(memberId)).where(freeBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }


//    자유 게시글 전체 리스트 가져오기
    @Override
    public Page<FreeBoard> findAllFreeBoardList(Pageable pageable) {
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
    public Page<FreeBoard> findFreeBoardListByCategoryType(Pageable pageable, CategoryType categoryType) {
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
    public Page<FreeBoard> findFreeBoardListByCategoryTypeAndMemberId(Pageable pageable, CategoryType categoryType, Long memberId) {
        List<FreeBoard> freeBoards = query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.freeCategory.eq(categoryType).and(freeBoard.member.id.eq(memberId)))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(freeBoard.count()).from(freeBoard).where(freeBoard.freeCategory.eq(categoryType).and(freeBoard.member.id.eq(memberId))).fetchOne();

        return new PageImpl<>(freeBoards, pageable, count);
    }


//    게시글 상세페이지 board 정보, 작성자 정보, 첨부파일
    @Override
    public Optional<FreeBoard> findFreeBoardAndFreeFilesById(Long boardId) {
        return Optional.ofNullable(query.select(freeBoard).from(freeBoard)
                .join(freeBoard.member).fetchJoin()
                .leftJoin(freeBoard.freeFiles).fetchJoin()
                .where(freeBoard.id.eq(boardId))
                .fetchOne());
    }

//    게시글 상세페이지 board 정보, reply정보
    @Override
    public Optional<FreeBoard> findFreeBoardAndFreeRepliesById(Long boardId) {
        return Optional.ofNullable(query.select(freeBoard).from(freeBoard)
                .leftJoin(freeBoard.freeReplies).fetchJoin()
                .where(freeBoard.id.eq(boardId))
                .fetchOne());
    }


}
