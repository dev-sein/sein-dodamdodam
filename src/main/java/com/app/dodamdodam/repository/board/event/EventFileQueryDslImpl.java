package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.repository.board.event.EventFileQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EventFileQueryDslImpl implements EventFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<EventFile> findByReviewBoardId(Long id) {
        return null;
    }
}
