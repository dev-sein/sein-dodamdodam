package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

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
    private String replyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public EventReview(Long id, String replyContent, String replyCount, EventBoard eventBoard, Member member) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyCount = replyCount;
        this.eventBoard = eventBoard;
        this.member = member;
    }
}
