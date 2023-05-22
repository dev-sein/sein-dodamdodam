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
    public EventReply(Long id, String replyContent, EventBoard eventBoard, Member member) {
        super(id, replyContent);
        this.eventBoard = eventBoard;
        this.member = member;
    }

    public EventReply(String replyContent, EventBoard eventBoard, Member member) {
        super(replyContent);
        this.eventBoard = eventBoard;
        this.member = member;
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
