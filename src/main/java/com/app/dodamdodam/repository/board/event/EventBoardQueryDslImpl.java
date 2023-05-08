package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.QEventBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventBoard.eventBoard;

@RequiredArgsConstructor
public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(eventBoard)
                .from(eventBoard)
                .where(eventBoard.member.id.eq(memberId))
                .orderBy(eventBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}

