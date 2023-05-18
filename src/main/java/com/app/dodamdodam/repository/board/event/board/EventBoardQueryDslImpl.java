package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.QEventBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.type.EventType;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;
import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;
import static com.app.dodamdodam.entity.event.QEventFile.eventFile;

public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    // 최신순 페이징처리
    @Override
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable) {
//        진행,진행전, 마감 순으로 목록 나오게 하기.
        Expression<Integer> eventStatusOrder = new CaseBuilder()
                .when(eventBoard.eventStatus.eq(EventType.PROGRESS)).then(1)
                .when(eventBoard.eventStatus.eq(EventType.HOLD)).then(2)
                .otherwise(3);

        List<EventBoard> eventBoards =  query.select(eventBoard)
                .from(eventBoard)
                .join(eventBoard.member).fetchJoin()
                .join(eventBoard.eventFiles).fetchJoin()
                .orderBy(eventBoard.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return checkLastPage(pageable, eventBoards);
    }

    //    hasNext true인지 false인지 체크하는 메소드(마지막 페이지 체크)
    private Slice<EventBoard> checkLastPage(Pageable pageable, List<EventBoard> eventBoards) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (eventBoards.size() > pageable.getPageSize()) {
            hasNext = true;
            eventBoards.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(eventBoards, pageable, hasNext);
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
