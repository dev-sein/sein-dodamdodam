package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import org.aspectj.weaver.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventLikeQueryDsl {
    // 멤버의 좋아요 체크
    public boolean checkMemberLikesEventBoard_QueryDSL(Long eventBoardId, Long memberId);

    //    찜목록
//    public Page<EventLike> findBookmarkListWithMemberIdWithPaging_QueryDSL(Pageable pageable, Long memberId);
}
