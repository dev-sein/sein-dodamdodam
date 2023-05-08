package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.repository.board.free.FreeBoardQueryDsl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventBoardRepository extends JpaRepository<EventBoard, Long>, EventBoardQueryDsl {
}
