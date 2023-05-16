package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventLike.eventLike;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Long findMemberByBoardLike(Long eventBoardId, Long memberId) {
        return null;
    }

    @Override
    public Long getEventLikeCount(Long eventBoardId) {
        return null;
    }

    @Override
    public void deleteByMemberIdAndEventBoardId(Long eventBoardId, Long memberId) {

    }

    @Override
    public Page<EventLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId) {
        return null;
    }

}
