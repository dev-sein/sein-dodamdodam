package com.app.dodamdodam.repository.file.event;

import com.app.dodamdodam.entity.event.EventFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventFileRepository extends JpaRepository<EventFile, Long>,EventFileQueryDsl {
}
