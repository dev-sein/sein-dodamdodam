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
    public Slice<EventReview> findAllByEventWithPaging_QueryDsl(Long eventBoardId, Pageable pageable) {
        List<EventReview> foundReview = query.select(eventReview)
                .from(eventReview)
                .leftJoin(eventReview.member, member)
                .fetchJoin()
                .where(eventReview.eventBoard.id.eq(eventBoardId))
                .orderBy(eventReview.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (foundReview.size() > pageable.getPageSize()) {
            foundReview.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundReview, pageable, hasNext);
    }

    @Override
    public Long getReviewCount_QueryDSL(Long eventBoardId) {
        return query.select(eventReview.count())
                .from(eventReview)
                .where(eventReview.eventBoard.id.eq(eventBoardId))
                .fetchOne();
    }

    @Override
    public void deleteByEventBoardId(Long eventBoardId) {
        query.delete(eventReview)
                .where(eventReview.eventBoard.id.eq(eventBoardId))
                .execute();
    }
}
