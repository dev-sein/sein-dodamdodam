package com.app.dodamdodam.repository.room;

import com.app.dodamdodam.entity.chatting.Room;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.app.dodamdodam.entity.purchase.PurchaseBoard;
import com.app.dodamdodam.search.FreeBoardSearch;
import com.app.dodamdodam.search.PurchaseBoardSearch;
import com.app.dodamdodam.search.chatting.RoomSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface RoomQueryDsl {
//    memberId 값으로 Room내역 조회
    public Slice<Room> findRoomByMemberId(Pageable pageable, Long memberId);

//    검색 - 무한스크롤
    public Slice<Room> findRoomSearchWithPaging_QueryDSL(RoomSearch roomSearch, Pageable pageable);

//    현재 시퀀스 조회
    public Room getCurrentSequence();

}
