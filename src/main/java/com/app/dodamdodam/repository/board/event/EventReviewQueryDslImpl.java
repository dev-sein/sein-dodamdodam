package com.app.dodamdodam.repository.board.event;

import com.app.dodamdodam.entity.event.EventReview;
import com.app.dodamdodam.entity.event.QEventReview;
import com.app.dodamdodam.repository.board.event.EventReviewQueryDsl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventReview.eventReview;

@RequiredArgsConstructor
public class EventReviewQueryDslImpl implements EventReviewQueryDsl {
    private final JPAQueryFactory query;

//    댓글 무한스크롤
    @Override
    public Slice<EventReview> findAllEventReplyByBoardId(Long boardId, Pageable pageable) {
        boolean hasNext = false;

        List<EventReview> eventReplies = query.select(eventReview)
                .from(eventReview)
                .join(eventReview.member)
                .fetchJoin()
                .orderBy(eventReview.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        if(eventReplies.size() > pageable.getPageSize()){
            hasNext = true;
            eventReplies.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(eventReplies, pageable,hasNext);
    }
}
