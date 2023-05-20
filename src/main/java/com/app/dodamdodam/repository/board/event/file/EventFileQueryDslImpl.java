package com.app.dodamdodam.repository.board.event.file;

import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.QEventFileDTO;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.repository.board.event.file.EventFileQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class EventFileQueryDslImpl implements EventFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<EventFileDTO> findAllFiles(LocalDateTime date) {
        return null;
    }
}
