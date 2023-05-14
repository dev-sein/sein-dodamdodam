package com.app.dodamdodam.repository.board.event.review;

import com.app.dodamdodam.entity.event.EventReview;
import com.app.dodamdodam.entity.event.QEventReview;
import com.app.dodamdodam.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventReview.eventReview;
import static com.app.dodamdodam.entity.member.QMember.member;

@RequiredArgsConstructor
public class EventReviewQueryDslImpl implements EventReviewQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<EventReview> findAllByEventBoardWithPaging_QueryDSL(Long eventBoardId, Pageable pageable) {
        List<EventReview> foundEventReview = query.select(eventReview)
                .from(eventReview)
                .leftJoin(eventReview.member, member)
                .fetchJoin()
                .where(eventReview.eventBoard.id.eq(eventBoardId))
                .orderBy(eventReview.id.desc())
                .offset(pageable.getOffset()-1)
                .limit(pageable.getPageSize()).fetch();

        Long count = getReviewCount_QueryDSL(eventBoardId);
        return new PageImpl<>(foundEventReview, pageable, count);
    }

    @Override
    public Long getReviewCount_QueryDSL(Long eventBoardId) {
        return query.select(eventReview.count())
                .from(eventReview)
                .where(eventReview.id.eq(eventBoardId))
                .fetchOne();
    }
}
