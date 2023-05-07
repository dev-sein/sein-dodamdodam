package com.app.dodamdodam.repository.chatting.chatting;

import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.chatting.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ChattingQueryDsl {
    //    id 값으로 Chatting내역 조회 무한스크롤
    public Slice<Chatting> findChattingByMemberId(Pageable pageable, Long id);
}
