package com.app.dodamdodam.entity.reply;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class Reply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
