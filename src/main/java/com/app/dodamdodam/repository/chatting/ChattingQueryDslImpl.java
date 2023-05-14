package com.app.dodamdodam.repository.chatting;


import com.app.dodamdodam.entity.chatting.Chatting;
import com.app.dodamdodam.type.ReadStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.management.Query;
import java.util.List;

import static com.app.dodamdodam.entity.chatting.QChatting.chatting;

@Slf4j
public class ChattingQueryDslImpl implements ChattingQueryDsl {
        @Autowired
        private JPAQueryFactory query;

//        룸 목록 클릭 시 MEMBERID를 넘겨받음. 이를 통해서 SENDERMEMBERID와 MEMBERID가 같은지 조회해서 채팅 내용을 조회한다.
        @Override
        public Slice<Chatting> findChattingByMemberId(Pageable pageable, Long memberId) {
                boolean hasNext = false;

                List<Chatting> chattings = query.select(chatting)
                    .from(chatting)
                    .where(chatting.senderMemberId.eq(memberId))
                    .orderBy(chatting.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();

                if (chattings.size() > pageable.getPageSize()){
                        hasNext = true;
                        chattings.remove(pageable.getPageSize());
                }

                return new SliceImpl<>(chattings, pageable, hasNext);
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


