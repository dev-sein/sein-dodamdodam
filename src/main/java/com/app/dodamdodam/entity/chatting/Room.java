package com.app.dodamdodam.entity.chatting;

import com.app.dodamdodam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Table(name = "TBL_ROOM")
@ToString(exclude = "chatting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private Long hostId;
    /*이게 필요한지? - 주석처리*/
    /*@NotNull
    private Long havingId;*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Chatting> chattings;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member; //FK

    public Room(Long hostId) {
        this.hostId = hostId;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
