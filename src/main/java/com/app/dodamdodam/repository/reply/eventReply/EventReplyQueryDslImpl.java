package com.app.dodamdodam.repository.reply.eventReply;

import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.entity.event.QEventReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventReply.eventReply;

@RequiredArgsConstructor
public class EventReplyQueryDslImpl implements EventReplyQueryDsl {
    private final JPAQueryFactory query;

//    댓글 무한스크롤
    @Override
    public Slice<EventReply> findAllEventReplyByBoardId(Long boardId, Pageable pageable) {
        boolean hasNext = false;

        List<EventReply> eventReplies = query.select(eventReply)
                .from(eventReply)
                .join(eventReply.member)
                .fetchJoin()
                .orderBy(eventReply.id.desc())
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
