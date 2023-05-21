package com.app.dodamdodam.repository.board.event.file;

import com.app.dodamdodam.domain.EventFileDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class EventFileQueryDslImpl implements EventFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<EventFileDTO> findAllFiles(LocalDateTime date) {
        return null;
    }
}
