package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.QEventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public class EventBoardQueryDslImpl implements EventBoardQueryDsl {
    @Override
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable) {
        return null;
    }
}
