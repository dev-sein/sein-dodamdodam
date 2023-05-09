package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QRoom.room;

public class RoomQueryDslImpl implements RoomQueryDsl{
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId) {
        return query.select(room).from(room).where(room.hostId.eq(memberId)).orderBy(room.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
    }
}
