package com.app.dodamdodam.repository.chatting.chatting;

import com.app.dodamdodam.entity.chatting.Chatting;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChattingQueryDsl {
    //    id 값으로 Chatting내역 조회 무한스크롤
//    public Slice<Chatting> findChattingByMemberId(Pageable pageable, Long id);
    public List<Chatting> findChattingByMemberId(Pageable pageable, Long memberId);
}
