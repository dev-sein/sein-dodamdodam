package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventFile;
import com.app.dodamdodam.type.EventType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class EventBoardDTO {

    /* 이벤트 보드 정보 다 받아오기*/
    private Long id;
    private String eventAddress;
    private String eventAddressDetail;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String eventIntroduction;
    private String boardTitle;
    private String boardContent;

    /* 좋아요 */
    private int eventLikeNumber;

    /*수락상태*/
    private EventType eventStatus;
    /*기업작성*/
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;
    /*멤버 정보 */
    private MemberDTO memberDTO;
    /*댓글 정보*/
    List<EventReviewDTO> reviews;
    /*파일*/
    private List<EventFile> eventFiles;

    /*메인 이미지*/
    private String eventFilePath;
    private String eventFileName;
    private String eventFileUuid;
    private String eventFileSize;

    private int endPage;

    private boolean checkLike;

    @QueryProjection
    public EventBoardDTO(Long id, String eventAddress, String eventAddressDetail, LocalDate eventStartDate, LocalDate eventEndDate, String eventIntroduction, String boardTitle, String boardContent, int eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, MemberDTO memberDTO, List<EventReviewDTO> reviews, List<EventFile> eventFiles, String eventFilePath, String eventFileName, String eventFileUuid, String eventFileSize, int endPage, boolean checkLike) {
        this.id = id;
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventIntroduction = eventIntroduction;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventLikeNumber = eventLikeNumber;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.memberDTO = memberDTO;
        this.reviews = reviews;
        this.eventFiles = eventFiles;
        this.eventFilePath = eventFilePath;
        this.eventFileName = eventFileName;
        this.eventFileUuid = eventFileUuid;
        this.eventFileSize = eventFileSize;
        this.endPage = endPage;
        this.checkLike = checkLike;
    }

    public EventBoard toEntity(){
        return EventBoard.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .eventAddress(eventAddress)
                .eventAddressDetail(eventAddressDetail)
                .eventStartDate(eventStartDate)
                .eventEndDate(eventEndDate)
                .eventBusinessNumber(eventBusinessNumber)
                .eventBusinessTel(eventBusinessTel)
                .eventBusinessEmail(eventBusinessEmail)
                .eventBusinessName(eventBusinessName)
                .eventFiles(eventFiles)
                .eventFilePath(eventFilePath)
                .eventFileName(eventFileName)
                .eventFileSize(eventFileSize)
                .eventFileUuid(eventFileUuid)
                .build();
    }


}
