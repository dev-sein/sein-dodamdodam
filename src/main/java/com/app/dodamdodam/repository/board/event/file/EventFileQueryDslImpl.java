package com.app.dodamdodam.repository.board.event.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.app.dodamdodam.entity.event.QEventFile.eventFile;

@RequiredArgsConstructor
public class EventFileQueryDslImpl implements EventFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public void deleteByEventBoardId(Long eventBoardId) {
        query.delete(eventFile)
                .where(eventFile.eventBoard.id.eq(eventBoardId))
                .execute();
    }
}
