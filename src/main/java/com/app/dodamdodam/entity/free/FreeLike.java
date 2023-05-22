package com.app.dodamdodam.entity.free;

import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FREE_BOARD_LIKE")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    /*좋아요 누른 회원의 id*/
    @NotNull
    private Long memberId;

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID", nullable = false)
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;

    public void setMemberId(Member member) {
        this.memberId = member.getId();
    }

    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }

    public FreeLike(Long memberId, FreeBoard freeBoard) {
        this.memberId = memberId;
        this.freeBoard = freeBoard;
    }
    /* 추가 */
}
