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
@ToString(exclude = {"eventReviews", "eventLikes"}, callSuper = true)
@Table(name = "TBL_EVENT_BOARD")
@NoArgsConstructor
        (access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Getter
public class EventBoard extends Board{

//    이벤트 게시글 비지니스 작성
    @NotNull private String eventAddress;
    @NotNull private String eventAddressDetail;
    @NotNull private LocalDate eventStartDate;
    @NotNull private LocalDate eventEndDate;
//    좋아요 수
    private Integer eventLikeNumber;

    /*리뷰 수*/
    @ColumnDefault(value="0")
    private Integer eventReviewCount;

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

//    게시글 댓글
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventBoard" , orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventReview> eventReviews;

// 작성한 유저
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
//  좋아요
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "eventBoard", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<EventLike> eventLikes = new ArrayList<>();


    @Builder
    public EventBoard(Long id, String boardTitle, String boardContent, String eventAddress, String eventAddressDetail, LocalDate eventStartDate, LocalDate eventEndDate, Integer eventLikeNumber, Integer eventReviewCount, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, List<EventFile> eventFiles, List<EventReview> eventReviews, Member member, List<EventLike> eventLikes) {
        super(id, boardTitle, boardContent);
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventLikeNumber = eventLikeNumber;
        this.eventReviewCount = eventReviewCount;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventFiles = eventFiles;
        this.eventReviews = eventReviews;
        this.member = member;
        this.eventLikes = eventLikes;
    }

    public EventBoard(String boardTitle, String boardContent){
        super(boardTitle,boardContent);
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setEventLikeCount(Integer eventLikeNumber){
        this.eventLikeNumber = eventLikeNumber;
    }

    public void setEventReviewCount(Integer eventReviewCount) {
        this.eventReviewCount = eventReviewCount;
    }


}