package com.app.dodamdodam.entity.chatting;

import com.app.dodamdodam.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table(name = "TBL_CHATTING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String chattingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
