package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = "freeBoard")
@Table(name = "TBL_FREE_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeReply extends Reply {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_ID")
//    @JsonBackReference
    private FreeBoard freeBoard;

    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }

    @Builder
    public FreeReply(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }

    @Builder
    public FreeReply(String replyContent) {
        super.setReplyContent(replyContent);
    }

    @Builder
    public FreeReply(String replyContent, Member member, FreeBoard freeBoard) {
        super(replyContent, member);
        this.freeBoard = freeBoard;
    }
}