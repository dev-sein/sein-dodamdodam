package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.QEventBoard;
import com.app.dodamdodam.entity.event.QEventFile;
import com.app.dodamdodam.entity.event.QEventLike;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.EventType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import javax.persistence.PreUpdate;
import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;
import static com.app.dodamdodam.entity.event.QEventFile.eventFile;
import static com.app.dodamdodam.entity.purchase.QPurchaseBoard.purchaseBoard;

@RequiredArgsConstructor
public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<EventBoard> findAllEventBoardWithPaging_QueryDSL(Pageable pageable) {
        List<EventBoard> founddBoard = query.select(eventBoard)
                .from(eventBoard)
                .leftJoin(eventBoard.eventFiles, eventFile)
                .fetchJoin()
                .where(eventBoard.eventStatus.eq(EventType.valueOf("Hold")))
                .where(eventBoard.eventStatus.eq(EventType.valueOf("PROGRESS")))
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset() -1 )
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(eventBoard.count())
                .from(eventBoard)
                .where(eventBoard.eventStatus.eq(EventType.valueOf("Hold")))
                .where(eventBoard.eventStatus.eq(EventType.valueOf("PROGRESS")))
                .fetchOne();

        return new PageImpl<>(founddBoard, pageable, count);
    }

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

    @Override
    public Page<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId) {
        List<EventBoard> eventBoards = query.select(eventBoard).from(eventBoard)
                .join(eventBoard.member).fetchJoin()
                .leftJoin(eventBoard.eventFiles).fetchJoin()
                .where(eventBoard.member.id.eq(memberId))
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(eventBoard.count()).from(eventBoard).where(eventBoard.member.id.eq(memberId)).fetchOne();

        return new PageImpl<>(eventBoards, pageable, count);
    }

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
