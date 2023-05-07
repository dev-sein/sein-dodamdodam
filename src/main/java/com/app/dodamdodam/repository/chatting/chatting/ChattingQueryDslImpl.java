package com.app.dodamdodam.repository.chatting.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public class ChattingQueryDslImpl implements ChattingQueryDsl {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public Slice<Chatting> findChattingByMemberId(Pageable pageable, Long id) {
        return null;
    }
}
