package com.app.dodamdodam.repository.chatting.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.entity.free.FreeBoard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;
import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;

public class ChattingQueryDslImpl implements ChattingQueryDsl {
        @Autowired
        private JPAQueryFactory query;

//        @Override
//        public Slice<Chatting> findChattingByMemberId(Pageable pageable, Long memberId) {
//            JPAQuery<Chatting> jpaQuery = query.selectFrom(chatting)
//                    .where(chatting.senderMemberId.eq(String.valueOf(memberId))
//                            .or(chatting.receiverMemberId.eq(String.valueOf(memberId))))
//                    .orderBy(chatting.createdDate.desc())
//                    .offset(pageable.getOffset())
//                    .limit(pageable.getPageSize());
//
//            List<Chatting> chatList = jpaQuery.fetch();
//
//            return chatList.isEmpty() ? null : new SliceImpl<>(chatList, pageable, jpaQuery.fetchCount() > pageable.getOffset() + chatList.size());

//            List<Chatting> chatList = query.selectFrom(chatting)
//                    .where(chatting.senderMemberId.eq(String.valueOf(memberId))
//                            .or(chatting.receiverMemberId.eq(String.valueOf(memberId))))
//                    .orderBy(chatting.createdDate.desc())
//                    .offset(pageable.getOffset())
//                    .limit(pageable.getPageSize())
//                    .fetch();
//
//            return chatList.isEmpty() ? null : new SliceImpl<>(chatList, pageable, query.fetchCount() > pageable.getOffset() + chatList.size());

            @Override
            public List<Chatting> findChattingByMemberId(Pageable pageable, Long memberId) {
                return query.select(chatting).from(chatting).where(chatting.senderMemberId.eq(String.valueOf(memberId))).orderBy(chatting.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
//                return query.select(chatting).from(chatting).where(chatting.senderMemberId.eq(memberId)).orderBy(chatting.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
            }


}


