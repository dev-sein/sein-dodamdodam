package com.app.dodamdodam.service.board.eventBoard;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.domain.FreeBoardFileDTO;
import com.app.dodamdodam.domain.PurchaseBoardDTO;
import com.app.dodamdodam.search.EventBoardSearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface EventBoardService {

    /* 검색 */
    public Slice<EventBoardDTO> getEventBoardsWithSearch(EventBoardSearch eventBoardSearch, Pageable pageable);

   public Page<EventBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable);
}
