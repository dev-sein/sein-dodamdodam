package com.app.dodamdodam.repository.file;

import com.app.dodamdodam.domain.FileDTO;
import com.app.dodamdodam.domain.QFileDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.app.dodamdodam.entity.file.QBoardFile.boardFile;

@RequiredArgsConstructor
public class FileQueryDslImpl implements FileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<FileDTO> findAllFiles(LocalDateTime date) {
        return query.select(new QFileDTO(boardFile.id,
                boardFile.fileOriginalName,
                boardFile.fileUuid,
                boardFile.filePath))
                .from(boardFile)
                .where(boardFile.filePath.eq(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))))
                .fetch();
    }

}
