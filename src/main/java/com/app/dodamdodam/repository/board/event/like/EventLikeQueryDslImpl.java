package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventLike;
import com.app.dodamdodam.entity.event.QEventLike;
import com.app.dodamdodam.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventLike.eventLike;

@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Member findMemberByBoardLike(Long eventBoardId, Long memberId){
        return query.select(eventLike.member)
                .from(eventLike)
                .where(eventLike.eventBoard.id.eq(eventBoardId))
                .where(eventLike.member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Long getEventLikeCount(Long eventBoardId){
        return query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.eventBoard.id.eq(eventBoardId))
                .fetchOne();
    }

    @Override
    public void deleteByMemberIdAndEventBoardId(Long eventBoardId, Long memberId){
        query.delete(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .where(eventLike.eventBoard.id.eq(eventBoardId))
                .execute();
    }

    @Override
    public Page<EventLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long memberId){
        List<EventLike> foundEventBoards = query.select(eventLike)
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .orderBy(eventLike.eventBoard.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundEventBoards, pageable, count);
    }

}
