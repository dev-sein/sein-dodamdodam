package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.Like.Likes;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_EVENT_LIKE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventLike extends Likes {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Builder
    public EventLike(EventBoard eventBoard, Member member) {
        this.eventBoard = eventBoard;
        this.member = member;
    }

}
