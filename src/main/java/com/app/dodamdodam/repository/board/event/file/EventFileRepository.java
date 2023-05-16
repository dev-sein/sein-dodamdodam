package com.app.dodamdodam.repository.board.event.file;

import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.repository.board.event.file.EventFileQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFile, Long>, EventFileQueryDsl {
}
