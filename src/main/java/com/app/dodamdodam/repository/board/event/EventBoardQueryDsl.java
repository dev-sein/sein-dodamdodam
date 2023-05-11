package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.domain.EventBoardDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import org.hibernate.Criteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface EventBoardQueryDsl {

    /*페이징처리 + 검색어로 찾기*/
    public List<EventBoardDTO> findAll(Pageable pageable, Criteria criteria);

    /*특정 목록 불러오기*/
    public EventBoardDTO readEventBoard(Long eventBoardId);

    /*게시글 검색*/





}
