package com.app.dodamdodam.repository.chatting.room;

import com.app.dodamdodam.entity.chatting.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, RoomQueryDsl {
}
