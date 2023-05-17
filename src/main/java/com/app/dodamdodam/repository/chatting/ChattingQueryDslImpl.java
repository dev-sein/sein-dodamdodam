package com.app.dodamdodam.repository.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import javax.management.Query;
import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;
import static com.app.dodamdodam.entity.chatting.QRoom.room;

@Slf4j
public class ChattingQueryDslImpl implements ChattingQueryDsl {
        @Autowired
        private JPAQueryFactory query;

//        룸 목록 클릭 시 MEMBERID를 넘겨받음. 이를 통해서 SENDERMEMBERID와 MEMBERID가 같은지 조회해서 채팅 내용을 조회한다.
        @Override
        public Page<Chatting> findChattingByRoomId_QueryDSL(Pageable pageable, Long roomId, Long memberId) {
                List<Chatting> chattings = query.select(chatting)
                    .from(chatting)
                    .where(chatting.senderMemberId.eq(memberId).and(chatting.room.id.eq(roomId)))
                    .orderBy(chatting.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
                Long count = query.select(chatting.count()).from(chatting)
                        .where(chatting.senderMemberId.eq(memberId).and(chatting.room.id.eq(roomId))).fetchOne();

                return new PageImpl<>(chattings, pageable, count);
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


