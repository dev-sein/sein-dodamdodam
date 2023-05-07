package com.app.dodamdodam.repository.chatting.room;

import com.app.dodamdodam.entity.chatting.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface RoomQueryDsl {
//    id 값으로 Room내역 조회 무한스크롤
    public Slice<Room> findRoomByMemberId(Pageable pageable, Long id);
}
