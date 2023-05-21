//package com.app.dodamdodam.entity.chatting;
//
//import com.app.dodamdodam.audit.Period;
//import com.app.dodamdodam.type.MessageType;
//import com.app.dodamdodam.type.ReadStatus;
//import com.sun.istack.NotNull;
//import lombok.*;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@ToString(exclude = "room")
//@Table(name = "TBL_CHATTING")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DynamicInsert
//@DynamicUpdate
//public class Chatting {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    @NotNull private Long senderMemberId;
//    @NotNull private Long receiverMemberId;
//    @NotNull private String chattingContent;
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private MessageType messageType;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @ColumnDefault("'UNREAD'")
//    private ReadStatus readStatus;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ROOM_ID")
//    private Room room;
//
//    public Chatting(Long senderMemberId, Long receiverMemberId, String chattingContent) {
//        this.senderMemberId = senderMemberId;
//        this.receiverMemberId = receiverMemberId;
//        this.chattingContent = chattingContent;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//}
