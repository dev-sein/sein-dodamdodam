package com.app.dodamdodam.entity.chatting;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;


}
