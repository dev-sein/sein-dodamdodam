package com.app.dodamdodam.repository.board.event.file;

import com.app.dodamdodam.domain.EventFileDTO;
import com.app.dodamdodam.entity.event.EventFile;

import java.time.LocalDateTime;
import java.util.List;

public interface EventFileQueryDsl {
    //    파일 가져오기
    public List<EventFileDTO> findAllFiles(LocalDateTime date);
}
