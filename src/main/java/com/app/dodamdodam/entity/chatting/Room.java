//package com.app.dodamdodam.entity.chatting;
//
//import com.app.dodamdodam.audit.Period;
//import com.app.dodamdodam.entity.member.Member;
//import com.sun.istack.NotNull;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter @Table(name = "TBL_ROOM")
//@ToString(exclude = "chattings")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Room extends Period {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    @NotNull
//    private Long hostId;
//    /*이게 필요한지? - 주석처리*/
//    @NotNull
//    private Long havingId;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = {CascadeType.REMOVE, CascadeType.PERSIST} )
//    private List<Chatting> chattings = new ArrayList<>();;
//
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member; //FK
//
//    public Room(Long hostId, Long havingId) {
//        this.hostId = hostId;
//        this.havingId = havingId;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//}
