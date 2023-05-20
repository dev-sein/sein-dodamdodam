//package com.app.dodamdodam.repository.room;
//
//import com.app.dodamdodam.entity.chatting.QChatting;
//import com.app.dodamdodam.entity.chatting.Room;
//import com.app.dodamdodam.search.chatting.RoomSearch;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//
//import java.util.List;
//
//import static com.app.dodamdodam.entity.chatting.QChatting.chatting;
//import static com.app.dodamdodam.entity.chatting.QRoom.room;
//import static com.app.dodamdodam.entity.free.QFreeBoard.freeBoard;
//
//
//public class RoomQueryDslImpl implements RoomQueryDsl{
//    @Autowired
//    private JPAQueryFactory query;
//
//
////    @Override
////    public List<Room> findRoomByMemberId(Pageable pageable, Long memberId) {
////        return query.select(room)
////                .from(room)
////                .join(room.chattings)
////                .fetchJoin()
////                .where(room.member.id.eq(memberId))
////                .orderBy(room.id.desc())
////                .offset(pageable.getOffset())
////                .limit(pageable.getPageSize())
////                .fetch();
////    }
//
//
////    룸 목록 무한스크롤
////    세션으로 넘겨받은 MEMBERID를 HOSTID와 같은지 조회 해서 ROOM 목록을 불러온다.
//    @Override
//    public Page<Room> findRoomByMemberId_QueryDSL(Pageable pageable, Long memberId) {
//        List<Room> rooms = query.select(room)
//                .from(room)
//                .where(room.hostId.eq(memberId))
//                .join(room.chattings).fetchJoin()
//                .orderBy(room.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        Long count = query.select(room.count()).from(room)
//                .where(room.hostId.eq(memberId)).fetchOne();
//
//        return new PageImpl<>(rooms, pageable, count);
//    }
//
////    현재 시퀀스 조회
//    @Override
//    public Room getCurrentSequence() {
//        return query.select(room)
//                .from(room)
//                .orderBy(room.id.desc())
//                .limit(1)
//                .fetchOne();
//    }
//
////    검색 무한스크롤
//    @Override
//    public Slice<Room> findRoomSearchWithPaging_QueryDSL(RoomSearch roomSearch, Pageable pageable) {
//        BooleanExpression memberNameContains = roomSearch.getMemberName() == null ? null : room.member.memberName.contains(roomSearch.getMemberName());
//        BooleanExpression chattingContentContains = roomSearch.getChattingContent() == null ? null : room.chattings.get(0).chattingContent.contains(roomSearch.getChattingContent());
//
//        boolean hasNext = false;
//
//        List<Room> rooms = query.select(room)
//                .from(room)
//                .join(room.chattings).fetchJoin()
//                .join(room.member).fetchJoin()
//                .where(memberNameContains, chattingContentContains)
//                .orderBy(room.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize() + 1)
//                .fetch();
//
//        if(rooms.size() > pageable.getPageSize()) {
//            hasNext = true;
//            rooms.remove(pageable.getPageSize()); // 한개 더 가져왔으니 더 가져온 데이터 삭제
//        }
//
//        return new SliceImpl<>(rooms, pageable, hasNext);
//    }
//
//
//}
