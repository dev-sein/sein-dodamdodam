package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

import static com.app.dodamdodam.entity.event.QEventLike.eventLike;


@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Long findMemberByEventLike_QueryDsl(Long eventBoardId, Long memberId) {
        return query.select(eventLike.member.count())
                .from(eventLike)
                .where(eventLike.eventBoard.id.eq(eventBoardId).and(eventLike.member.id.eq(memberId)))
                .fetchOne();
    }

    @Override
    public Long getEventBoardLikeCount_QueryDsl(Long eventBoardId) {
        return query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.eventBoard.id.eq(eventBoardId))
                .fetchOne();
    }

    @Override @Transactional
    public void deleteByMemberIdAndEventBoardId_QueryDsl(Long eventBoardId, Long memberId) {
        query.delete(eventLike)
                .where(eventLike.member.id.eq(memberId).and(eventLike.eventBoard.id.eq(eventBoardId)))
                .execute();
    }

    @Override
    public Page<EventLike> findByLikeMemberIdWithPaging_QueryDsl(Pageable pageable, Long id) {
        List<EventLike> foundSuggest = query.select(eventLike)
                .from(eventLike)
                .where(eventLike.member.id.eq(id))
                .orderBy(eventLike.eventBoard.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundSuggest, pageable, count);
    }

}
