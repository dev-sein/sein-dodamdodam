package com.app.dodamdodam.entity.chatting;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_CHATTING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private Long senderMemberId;
    private Long receiverMemberId;
    private String chattingContent;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    public Chatting(Long senderMemberId, Long receiverMemberId, String chattingContent) {
        this.senderMemberId = senderMemberId;
        this.receiverMemberId = receiverMemberId;
        this.chattingContent = chattingContent;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
