package com.app.dodamdodam.repository.file;

import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.domain.QEventFileDTO;
import com.app.dodamdodam.entity.event.QEventFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.app.dodamdodam.entity.event.QEventFile.eventFile;
import static com.app.dodamdodam.entity.file.QBoardFile.boardFile;

@RequiredArgsConstructor
public class FileQueryDslImpl implements FileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<EventFileDTO> findAllFiles(LocalDateTime date) {
        return query.select(new QEventFileDTO(eventFile.id,eventFile.fileOriginalName,eventFile.fileUuid,eventFile.filePath,eventFile.fileSize))
                .from(eventFile)
                .where(eventFile.filePath.eq(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))))
                .fetch();
    }
}
