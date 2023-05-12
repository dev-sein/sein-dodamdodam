package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.QChatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.search.chatting.RoomSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;
import static com.app.dodamdodam.entity.chatting.QRoom.room;


public class RoomQueryDslImpl implements RoomQueryDsl{
    @Autowired
    private JPAQueryFactory query;


//    @Override
//    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId) {
//        return query.select(room)
//                .from(room)
//                .join(room.chattings)
//                .fetchJoin()
//                .where(room.member.id.eq(memberId))
//                .orderBy(room.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//    }

//    세션으로 넘겨받은 MEMBERID를 HOSTID와 같은지 조회 해서 ROOM 목록을 불러온다.
    @Override
    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId) {
        return query.select(room)
                .from(room)
                .join(room.chattings).fetchJoin()
                .where(room.hostId.eq(memberId))
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Room getCurrentSequence() {
        return query.select(room)
                .from(room)
                .orderBy(room.id.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public Page<Room> findRoomSearchWithPaging_QueryDSL(RoomSearch roomSearch, Pageable pageable) {
        BooleanExpression memberNameEq = roomSearch.getMemberName() == null ? null : room.member.memberName.eq(roomSearch.getMemberName());
        BooleanExpression chattingContentEq = roomSearch.getChattingContent() == null ? null : room.chattings.get(0).chattingContent.eq(roomSearch.getChattingContent());

        List<Room> rooms = query.select(room)
                .from(room)
                .join(room.member).fetchJoin()
                .join(room.chattings).fetchJoin()
                .where(memberNameEq, chattingContentEq)
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(room.count()).where(memberNameEq, chattingContentEq).from(room).fetchOne();

        return new PageImpl<>(rooms, pageable, count);
    }


}
