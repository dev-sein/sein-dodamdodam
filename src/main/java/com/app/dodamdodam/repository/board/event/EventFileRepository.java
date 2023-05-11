package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.repository.board.event.EventFileQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFile, Long>, EventFileQueryDsl {
}
