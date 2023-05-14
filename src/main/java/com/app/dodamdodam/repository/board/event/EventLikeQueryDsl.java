package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import com.app.dodamdodam.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventLikeQueryDsl {
//    멤버 좋아요 체크
    public boolean checkMemberLikesEventBoard_QueryDSL(Member member, EventBoard eventBoard);

//  찜
    public Page<EventLike> findBookMarkListWithMemberIdWithPaging_QueryDSL(Pageable pageable, Long memberId);
}
