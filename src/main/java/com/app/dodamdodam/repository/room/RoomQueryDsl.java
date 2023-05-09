package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QRoom.room;

public interface RoomQueryDsl {
//    id 값으로 Room내역 조회 무한스크롤
    public List<Room> findRoomByMemberId(Pageable pageable, Long id);
}
