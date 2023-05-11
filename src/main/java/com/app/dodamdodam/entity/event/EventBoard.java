package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.EventType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@ToString(callSuper = true)
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class EventBoard extends Board {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private LocalDate eventEndDate;
    @NotNull private String eventIntroduction;
    @NotNull private String eventMemberCount;
    //    수락대기, 수락, 수락거절
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    @NotNull private EventType eventStatus;
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard")
    private List<EventFile> eventFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard")
    private List<EventReply> eventReplies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private EventLike eventLike;

    public EventBoard(String boardTitle, String boardContent){
        super(boardTitle,boardTitle);
    }

    public void setMember(Member member) {
        this.member = member;
    }

}