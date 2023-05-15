package com.app.dodamdodam.entity.event;

import com.app.dodamdodam.audit.Period;
import com.app.dodamdodam.domain.EventBoardDTO;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = {"member"})
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor
        (access = AccessLevel.PROTECTED)
public class EventBoard extends Board{
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long eventBoardid; //pk
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private LocalDate eventEndDate;
//    좋아요 수
    private Integer eventLikeNumber;

    //    진행전, 진행중, 진행마감
    @ColumnDefault("'APPLYING'")
    @Enumerated(EnumType.STRING)
     private EventType eventStatus;

     private String eventBusinessNumber;
     private String eventBusinessName;
     private String eventBusinessTel;
     private String eventBusinessEmail;

     /*메인 사진*/
    @NotNull private String eventFilePath;
    @NotNull private String eventFileName;
    @NotNull private String eventFileUuid;
     private String eventFileSize;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard")
    private List<EventFile> eventFiles;


//    게시글 댓글
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard" , orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventReview> eventreviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
//  좋아요
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "eventBoard", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventLike> eventLikes = new ArrayList<>();



    public EventBoard(String boardTitle, String boardContent){
        super(boardTitle,boardContent);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setEventLikeCount(Integer eventLikeNumber){
        this.eventLikeNumber = eventLikeNumber;
    }

    @Builder
    public EventBoard(String boardTitle, String boardContent, Long eventBoardid, String eventAddress, String eventAddressDetail, LocalDate eventStartDate, LocalDate eventEndDate, Integer eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, String eventFilePath, String eventFileName, String eventFileUuid, String eventFileSize, List<EventFile> eventFiles, List<EventReview> eventreviews, Member member, List<EventLike> eventLikes) {
        super(boardTitle, boardContent);
        this.eventBoardid = eventBoardid;
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventLikeNumber = eventLikeNumber;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventFilePath = eventFilePath;
        this.eventFileName = eventFileName;
        this.eventFileUuid = eventFileUuid;
        this.eventFileSize = eventFileSize;
        this.eventFiles = eventFiles;
        this.eventreviews = eventreviews;
        this.member = member;
        this.eventLikes = eventLikes;
    }

//    update
    public void Update(EventBoardDTO eventBoardDTO){
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventFilePath = eventFilePath;
        this.eventFileName = eventFileName;
        this.eventFileUuid = eventFileUuid;
        this.eventFileSize = eventFileSize;
        this.eventFiles = eventFiles;
    }


    public void updateEventLikeNumberPlus(){this.eventLikeNumber++;}
    public void updateEventLikeNumberMinus(){this.eventLikeNumber--;}

}