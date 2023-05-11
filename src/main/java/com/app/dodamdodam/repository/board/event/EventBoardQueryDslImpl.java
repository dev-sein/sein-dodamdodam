package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.QEventBoardDTO;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    @Override
    public List<EventBoardDTO> findAll(Pageable pageable, Criteria criteria) {
        return null;
    }

    @Override
    public EventBoardDTO readEventBoard(Long eventBoardId) {
        return null;
    }
}
