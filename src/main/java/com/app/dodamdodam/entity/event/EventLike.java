package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"eventBoard"})
@Table(name = "TBL_EVENT_LIKE")
@NoArgsConstructor
public class EventLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    @NotNull
    @OnDelete(action= OnDeleteAction.CASCADE)
    private EventBoard eventBoard;


    public EventLike(EventBoard eventBoard) {
        this.eventBoard = eventBoard;
    }

//    public void setMember(Member member){
//        this.memberId = member.getMemberId();
//    }

    public void setEventBoard(EventBoard eventBoard){
        this.eventBoard = eventBoard;
    }
}
