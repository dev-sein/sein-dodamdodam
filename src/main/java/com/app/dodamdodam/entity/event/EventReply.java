package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.audit.Period;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_EVENT_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_BOARD_ID")
    private EventBoard eventBoard;

}
