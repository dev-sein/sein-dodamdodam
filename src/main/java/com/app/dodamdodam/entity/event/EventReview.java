package com.app.dodamdodam.entity.event;

import antlr.debug.Event;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventReview extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private EventBoard eventBoard; //fk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;


    public void setEventBoard(EventBoard eventBoard){
        this.eventBoard = eventBoard;
    }

    public void setMember(Member member){
        this.member = member;
    }


    public EventReview(String replyContent, Member member, EventBoard eventBoard) {
        super(replyContent, member);
        this.eventBoard = eventBoard;
        this.member = member;
    }
}
