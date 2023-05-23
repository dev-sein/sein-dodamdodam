package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventLikeQueryDsl {
    public Long findMemberByEventLike_QueryDsl(Long eventBoardId, Long memberId);

    public Long getEventBoardLikeCount_QueryDsl(Long eventBoardId);

    public void deleteByMemberIdAndEventBoardId_QueryDsl(Long eventBoardId, Long memberId);

    public Page<EventLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long id);
}
