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
@ToString(exclude = "eventBoard")
@Table(name = "TBL_EVENT_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventReview extends Reply {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private int replyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private EventBoard eventBoard; //fk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @NotNull
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Member member;


    public void setEventBoard(EventBoard eventBoard){
        this.eventBoard = eventBoard;
    }

    public void setMember(Member member){
        this.member = member;
    }

    @Builder
    public EventReview(Long id, String replyContent, int replyCount, EventBoard eventBoard, Member member) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyCount = replyCount;
        this.eventBoard = eventBoard;
        this.member = member;
    }

    public void update(String replyContent){
        this.replyContent = replyContent;
    }
}
