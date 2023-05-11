package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface EventBoardQueryDsl {

//  목록 페이징(최신)
    public Slice<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable);

//




}
