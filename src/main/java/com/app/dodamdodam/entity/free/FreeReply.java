package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.event.EventBoard;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_FREE_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeReply extends Period{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;

}
