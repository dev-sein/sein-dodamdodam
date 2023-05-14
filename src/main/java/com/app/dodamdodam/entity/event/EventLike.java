package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EVENT_BOARD_ID")
    @NotNull
    private EventBoard eventBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public EventLike(EventBoard eventBoard, Member member) {
        this.eventBoard = eventBoard;
        this.member = member;
    }
}
