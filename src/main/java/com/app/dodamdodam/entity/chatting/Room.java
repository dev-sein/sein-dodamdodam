package com.app.dodamdodam.entity.chatting;

import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Table(name = "TBL_ROOM")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private int hostId;
    @NotNull
    private int havingId;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CHATTING_ID")
//    private List<Chatting> chattings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;




}
