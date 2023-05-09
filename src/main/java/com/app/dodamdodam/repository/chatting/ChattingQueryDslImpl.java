package com.app.dodamdodam.repository.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;

public class ChattingQueryDslImpl implements ChattingQueryDsl {
        @Autowired
        private JPAQueryFactory query;

        @Override
        public List<Chatting> findChattingByMemberId(Pageable pageable, Long memberId) {
            return query.select(chatting).from(chatting).where(chatting.senderMemberId.eq(memberId)).orderBy(chatting.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        }


}


