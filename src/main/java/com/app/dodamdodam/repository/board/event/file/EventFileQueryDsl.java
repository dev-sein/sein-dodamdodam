package com.app.dodamdodam.repository.board.event.file;

import com.app.dodamdodam.entity.event.EventFile;

import java.util.List;

public interface EventFileQueryDsl {
    //    파일 가져오기
    public List<EventFile> findByReviewBoardId(Long id);
}
