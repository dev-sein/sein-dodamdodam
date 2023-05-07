package com.app.dodamdodam.repository.chatting.room;

import com.app.dodamdodam.entity.chatting.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;


public class RoomQueryDslImpl implements RoomQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId){

        return null;
    }

//    @Override
//    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId) {
//        return query.select(freeBoard).from(freeBoard).where(freeBoard.member.id.eq(memberId)).orderBy(freeBoard.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
//    }

//    @Override
//    public List<Point> findPointByMemberId(Long memberId) {
//        return query.select(point).from(point).where(point.member.id.eq(memberId)).orderBy(point.id.desc()).fetch();
//    }
}
