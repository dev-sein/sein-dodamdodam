package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBoardRepository extends JpaRepository<EventBoard, Long>, EventBoardQueryDsl {
}
