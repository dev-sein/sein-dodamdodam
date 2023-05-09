package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QRoom.room;

public interface RoomQueryDsl {
//    memberId 값으로 Room내역 조회
    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId);

}
