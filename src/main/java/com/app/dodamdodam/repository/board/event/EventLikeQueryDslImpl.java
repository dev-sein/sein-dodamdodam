package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventBoard;
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
    public boolean checkMemberLikesEventBoard_QueryDSL(Member member, EventBoard eventBoard) {
        Long count = query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.member.eq(member).and(eventLike.eventBoard.eq(eventBoard)))
                .fetchOne();
        return count> 0 ;
    }

    @Override
    public Page<EventLike> findBookMarkListWithMemberIdWithPaging_QueryDSL(Pageable pageable, Long memberId) {

        List<EventLike> eventLikeList = query.select(eventLike)
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .orderBy(eventLike.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.member.id.eq(memberId))
                .fetchOne();
        return new PageImpl<>(eventLikeList, pageable, count);
    }
}
