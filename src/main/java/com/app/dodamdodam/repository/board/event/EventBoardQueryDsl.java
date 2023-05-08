package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventBoardQueryDsl {

    //    세션에 담긴 id 값 받아와서 내가 작성한 이벤트 리스트 가져오기
    public List<EventBoard> findEventBoardListByMemberId(Pageable pageable, Long memberId);
}
