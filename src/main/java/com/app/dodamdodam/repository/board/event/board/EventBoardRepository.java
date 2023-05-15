package com.app.dodamdodam.repository.board.event.board;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.repository.board.event.board.EventBoardQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBoardRepository extends JpaRepository<EventBoard, Long>, EventBoardQueryDsl {
}
