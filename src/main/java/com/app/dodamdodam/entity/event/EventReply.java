package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true,exclude = "eventBoard")
@Table(name = "TBL_EVENT_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard; //fk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public EventReply(Long id, String replyContent, Member member, EventBoard eventBoard, Member member1) {
        super(id, replyContent, member);
        this.eventBoard = eventBoard;
        this.member = member1;
    }

    public EventReply(String replyContent, Member member, EventBoard eventBoard, Member member1) {
        super(replyContent, member);
        this.eventBoard = eventBoard;
        this.member = member1;
    }


    public void setEventReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }

    public void setEventBoard(EventBoard eventBoard){
        this.eventBoard = eventBoard;
    }

    public void setMember(Member member){
        this.member = member;
    }


}
