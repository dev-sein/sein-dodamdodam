package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.QEventBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.EventType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;
import static com.app.dodamdodam.entity.event.QEventFile.eventFile;

public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    /*검색 + 목록*/
    @Override
    public Page<EventBoard> findEventBoardBySearchWithPaging_QueryDSL(EventBoardSearch eventBoardSearch, Pageable pageable, EventType eventStatus) {
        BooleanExpression writerNameEq = eventBoardSearch.getWriterName() == null ? null : eventBoard.member.memberName.contains(eventBoardSearch.getWriterName());
        BooleanExpression eventTitleEq = eventBoardSearch.getBoardTitle() == null ? null : eventBoard.boardTitle.contains(eventBoardSearch.getBoardTitle());
        BooleanExpression eventContentEq = eventBoardSearch.getBoardContent() == null ? null : eventBoard.boardContent.contains(eventBoardSearch.getBoardContent());

        List<EventBoard> eventBoards = query.select(eventBoard)
                .from(eventBoard)
                .join(eventBoard.member).fetchJoin()
                .leftJoin(eventBoard.eventFiles).fetchJoin()
                .where(writerNameEq.or(eventTitleEq).or(eventContentEq))
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(eventBoard.count()).where(writerNameEq.or(eventTitleEq).or(eventContentEq)).from(eventBoard).fetchOne();

        return new PageImpl<>(eventBoards, pageable, count);
    }

    @Override
    public EventBoard getCurrentSequence_QueryDsl() {
        return query.select(eventBoard)
                .from(eventBoard)
                .orderBy(eventBoard.id.desc())
                .limit(1)
                .fetchOne();
    }

    //    상세보기
    @Override
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long eventBoardId) {
        EventBoard eventBoard = query.select(QEventBoard.eventBoard)
                .from(QEventBoard.eventBoard)
                .join(QEventBoard.eventBoard.member).fetchJoin()
                .join(QEventBoard.eventBoard.eventFiles).fetchJoin()
                .where(QEventBoard.eventBoard.id.eq(eventBoardId))
                .fetchOne();
        return Optional.of(eventBoard);
    }

    // 게시글 검색
    @Override
    public List<EventBoard> findAllWithSearch(EventBoardSearch eventBoardSearch) {
        BooleanExpression eventTitleLike = eventBoardSearch.getBoardTitle() == null ? null : eventBoard.boardTitle.like(eventBoardSearch.getBoardTitle());
        BooleanExpression eventContentLike = eventBoardSearch.getBoardContent() == null ? null : eventBoard.boardContent.like(eventBoardSearch.getBoardContent());
        List<EventBoard> eventBoards = query.select(eventBoard)
                .from(eventBoard)
                .where(eventTitleLike)
                .where(eventContentLike)
                .leftJoin(eventBoard.eventFiles, eventFile)
                .orderBy(eventBoard.id.desc())
                .fetch();

        return eventBoards;
    }


}
