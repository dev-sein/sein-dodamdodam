package com.app.dodamdodam.repository.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;

public class ChattingQueryDslImpl implements ChattingQueryDsl {
        @Autowired
        private JPAQueryFactory query;

//        룸 목록 클릭 시 MEMBERID를 넘겨받음. 이를 통해서 SENDERMEMBERID와 MEMBERID가 같은지 조회해서 채팅 내용을 조회한다.
        @Override
        public List<Chatting> findChattingByMemberId(Pageable pageable, Long memberId) {
            return query.select(chatting)
                    .from(chatting)
                    .where(chatting.senderMemberId.eq(memberId))
                    .orderBy(chatting.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        @Override
        public Integer findUnreadChattingByMemberId(Long memberId, Long roomId){
                return query
                        .select(chatting.chattingContent.count().sum())
                        .where(chatting.room.id.eq(roomId)
                                .and(chatting.receiverMemberId.eq(memberId)
                                        .and(chatting.readStatus.eq(ReadStatus.UNREAD))))
                        .fetch()
                        .size();
        }


}


