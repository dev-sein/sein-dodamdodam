package com.app.dodamdodam.entity.reply;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
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
    @NotNull
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Reply(String replyContent, Member member) {
        this.replyContent = replyContent;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}