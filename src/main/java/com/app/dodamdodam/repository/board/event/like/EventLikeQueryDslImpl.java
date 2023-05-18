package com.app.dodamdodam.repository.board.event.like;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventLike.eventLike;


@RequiredArgsConstructor
public class EventLikeQueryDslImpl implements EventLikeQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public boolean checkMemberLikesEventBoard_QueryDSL(Long eventBoardId, Long memberId) {
        Long count = query.select(eventLike.count())
                .from(eventLike)
                .where(eventLike.eventBoard.id.eq(eventBoardId).and(eventLike.member.id.eq(memberId)))
                .fetchOne();
        return count > 0;
    }

}
