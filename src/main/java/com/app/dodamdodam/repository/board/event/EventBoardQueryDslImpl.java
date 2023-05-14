package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.search.EventBoardSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;

@RequiredArgsConstructor
public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable) {
        return null;
    }


    @Override
    public Optional<EventBoard> findEventBoardById_QueryDSL(Long boardId) {
        return Optional.ofNullable(
                query.select(eventBoard)
                        .from(eventBoard)
                        .join(eventBoard.member)
                        .fetchJoin()
                        .join(eventBoard.eventFiles)
                        .fetchJoin()
                        .where(eventBoard.id.eq(boardId))
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
    public Slice<EventBoard> findAllWithSearch_QueryDSL(EventBoardSearch eventBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleContains = eventBoardSearch.getBoardTitle() == null ? null : eventBoard.boardTitle.contains(eventBoardSearch.getBoardTitle());
        BooleanExpression boardContentContains = eventBoardSearch.getBoardContent() == null ? null : eventBoard.boardContent.contains(eventBoardSearch.getBoardContent());

        boolean hasNext = false;

        List<EventBoard> eventBoards = query.select(eventBoard)
                .from(eventBoard)
                .join(eventBoard.eventFiles)
                .fetchJoin()
                .join(eventBoard.member)
                .fetchJoin()
                .where(boardTitleContains, boardContentContains)
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        if(eventBoards.size() > pageable.getPageSize()) {
            hasNext = true;
            eventBoards.remove(pageable.getPageSize()); // 한개 더 가져왔으니 더 가져온 데이터 삭제
        }

        return new SliceImpl<>(eventBoards, pageable, hasNext);
    }

}
