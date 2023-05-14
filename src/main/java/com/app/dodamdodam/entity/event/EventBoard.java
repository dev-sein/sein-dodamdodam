package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.member.Member;
import com.app.dodamdodam.type.EventType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @NotNull private LocalDate eventEndDate;
    @NotNull private String eventIntroduction;
//    좋아요 수
    private int eventLikeNumber;

    //    진행전, 진행중, 진행마감
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
     private EventType eventStatus;

     private String eventBusinessNumber;
     private String eventBusinessName;
     private String eventBusinessTel;
     private String eventBusinessEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard")
    private List<EventFile> eventFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard")
    private List<EventReview> eventreviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "eventBoard", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventLike> eventLikes = new ArrayList<>();



    public EventBoard(String boardTitle, String boardContent){
        super(boardTitle,boardTitle);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setEventLikeCount(Integer eventLikeNumber){
        this.eventLikeNumber = eventLikeNumber;
    }

    @Builder
    public EventBoard(String eventAddress, String eventAddressDetail, LocalDate eventStartDate,LocalDate eventEndDate ,String eventIntroduction, int eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, List<EventFile> eventFiles, List<EventReview> eventreviews, Member member, List<EventLike> eventLikes) {
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventIntroduction = eventIntroduction;
        this.eventLikeNumber = eventLikeNumber;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventFiles = eventFiles;
        this.eventreviews = eventreviews;
        this.member = member;
        this.eventLikes = eventLikes;
    }


}