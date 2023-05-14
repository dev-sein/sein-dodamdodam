package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.QEventBoard;
import com.app.dodamdodam.entity.event.QEventFile;
import com.app.dodamdodam.entity.event.QEventLike;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.EventType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import javax.persistence.PreUpdate;
import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;
import static com.app.dodamdodam.entity.event.QEventFile.eventFile;
import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;

public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    //    이벤트 정보 게시글 전체 리스트 가져오기
    @Override
    public Page<EventBoard> findAllEventBoardList_QueryDSL(Pageable pageable) {
        List<EventBoard> eventBoards = query.selectFrom(eventBoard)
                .leftJoin(eventBoard.eventFiles).fetchJoin()
                .join(eventBoard.member).fetchJoin()
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.id.count()).from(freeBoard)
                .fetchOne();

        return new PageImpl<>(eventBoards, pageable, count);
    }

    //     상세보기
    @Override
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId) {
        return Optional.ofNullable(
                query.select(eventBoard)
                        .from(eventBoard)
                        .join(eventBoard.member)
                        .fetchJoin()
                        .join(eventBoard.eventFiles)
                        .fetchJoin()
                        .where(eventBoard.id.eq(eventBoardId))
                        .fetchOne()
        );
    }

    //    자유게시글 상세 페이지 댓글
    @Override
    public Optional<EventBoard> findEventBoardAndEventRepliesById_QueryDSL(Long boardId) {
        return Optional.ofNullable(query.select(eventBoard).from(eventBoard)
                .leftJoin(eventBoard.eventreviews).fetchJoin()
                .where(eventBoard.id.eq(boardId))
                .fetchOne());
    }

// 게시글 검색
    @Override
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch) {
        BooleanExpression eventTitleLike = eventBoardSearch.getBoardTitle() == null ? null : eventBoard.boardTitle.like(eventBoardSearch.getBoardTitle());
        List<EventBoard> eventBoards = query.select(eventBoard)
                .from(eventBoard)
                .where(eventTitleLike)
                .leftJoin(eventBoard.eventFiles, eventFile)
                .orderBy(eventBoard.id.desc())
                .fetch();

        return eventBoards;
    }


}
