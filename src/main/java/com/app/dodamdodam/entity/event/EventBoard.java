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
@NoArgsConstructor
        (access = AccessLevel.PROTECTED)
public class EventBoard extends Board {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private String eventIntroduction;
//    조회수
     private int eventViewNumber;
//    좋아요 수
    private int eventLikeNumber;

    //    수락대기, 수락, 수락거절
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
    @NotNull private EventType eventStatus;
    @NotNull private String eventBusinessNumber;
    @NotNull private String eventBusinessName;
    @NotNull private String eventBusinessTel;
    @NotNull private String eventBusinessEmail;

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

    @Builder
    public EventBoard(String eventAddress, String eventAddressDetail, LocalDate eventStartDate, String eventIntroduction, int eventViewNumber, int eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, List<EventFile> eventFiles, List<EventReply> eventReplies, Member member, EventLike eventLike) {
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventIntroduction = eventIntroduction;
        this.eventViewNumber = eventViewNumber;
        this.eventLikeNumber = eventLikeNumber;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventFiles = eventFiles;
        this.eventReplies = eventReplies;
        this.member = member;
        this.eventLike = eventLike;
    }


//   조회수
    public void updateReadCount(){
        this.eventViewNumber++;
    }

    //    좋아요 업데이트
    public void updateLikePlusCount(){this.eventLikeNumber++;}

    public void updateLikeMinusCount(){
        this.eventLikeNumber--;
    }
}