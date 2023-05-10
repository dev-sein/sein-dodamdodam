package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.QChatting;
import com.app.dodamdodam.entity.chatting.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
