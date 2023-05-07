package com.app.dodamdodam.repository.chatting.room;

import com.app.dodamdodam.entity.chatting.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QRoom.room;


public class RoomQueryDslImpl implements RoomQueryDsl {
    @Autowired
    private JPAQueryFactory query;

//    페이징 처리
//    @Override
//    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId){
//        return query.select(room).from(room).where(room.member.id.eq(memberId)).orderBy(room.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
//    }

//    무한스크롤 채팅 목록 조회
    @Autowired
    public Slice<Room> findRoomByMemberId(Pageable pageable, Long memberId) {
        // 전체 목록 가져오기
        List<Room> rooms = query.selectFrom(room)
                .where(room.member.id.eq(memberId))
                .orderBy(room.id.desc())
                .fetch();

        // 페이징 처리된 목록 만들기
        List<Room> pagedRooms = query.selectFrom(room)
                .where(room.member.id.eq(memberId))
                .orderBy(room.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 슬라이싱 처리된 목록 만들기
        int toIndex = (int) pageable.getOffset() + pageable.getPageSize();
        List<Room> slicedRooms = pagedRooms.subList((int) pageable.getOffset(), Math.min(toIndex, rooms.size()));

        // 다음 페이지 여부 체크
        boolean hasNext = toIndex < rooms.size();

        // 반환할 슬라이스 객체 생성
        return new SliceImpl<>(slicedRooms, pageable, hasNext);
    }
}
