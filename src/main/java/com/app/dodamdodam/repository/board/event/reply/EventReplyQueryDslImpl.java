package com.app.dodamdodam.repository.board.event.reply;

import com.app.dodamdodam.entity.event.EventReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;

import static com.app.dodamdodam.entity.event.QEventReply.eventReply;
import static com.app.dodamdodam.entity.member.QMember.member;

@RequiredArgsConstructor
public class EventReplyQueryDslImpl implements EventReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<EventReply> findAllByEventWithPaging_QueryDsl(Long eventBoardId, Pageable pageable) {
        List<EventReply> foundReply = query.select(eventReply)
                .from(eventReply)
                .leftJoin(eventReply.member, member)
                .fetchJoin()
                .where(eventReply.eventBoard.id.eq(eventBoardId))
                .orderBy(eventReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (foundReply.size() > pageable.getPageSize()) {
            foundReply.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundReply, pageable, hasNext);
    }

    @Override
    public Long getReplyCount_QueryDsl(Long eventBoardId) {
        return query.select(eventReply.count())
                .from(eventReply)
                .where(eventReply.eventBoard.id.eq(eventBoardId))
                .fetchOne();
    }

    @Override
    public void deleteByEventBoardId(Long eventBoardId) {
        query.delete(eventReply)
                .where(eventReply.eventBoard.id.eq(eventBoardId))
                .execute();
    }

    //boardId로 그 board에 달린 댓글 가져오기
    @Override
    public Page<EventReply> findEventRepliesByBoardId_QueryDsl(Pageable pageable, Long boardId) {
        List<EventReply> eventReplies = query.select(eventReply).from(eventReply)
                .join(eventReply.member).fetchJoin()
                .where(eventReply.eventBoard.id.eq(boardId))
                .orderBy(eventReply.id.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(eventReply.count()).from(eventReply).where(eventReply.eventBoard.id.eq(boardId)).fetchOne();

        return new PageImpl<>(eventReplies, pageable, count);
    }
}
