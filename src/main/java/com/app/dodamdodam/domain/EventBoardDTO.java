package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.board.Board;
import com.app.dodamdodam.entity.event.EventBoard;
import com.querydsl.core.annotations.QueryProjection;
import jdk.jfr.Event;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class EventBoardDTO {

    private Long id;
    private String boardTitle;
    private String boardContent;

    private List<EventFileDTO> eventFileDTOS;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private MemberDTO memberDTO;

    private String eventAddress;
    private String eventAddressDetail;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String eventIntroduction;
    private String eventMemberCount;
    private int eventViewNumber;
    private int eventLikeNumber;



    private List<EventBoardDTO> eventBoardList;


    @QueryProjection
    public EventBoardDTO(Long id, String boardTitle, String boardContent, List<EventFileDTO> eventFileDTOS, LocalDateTime createdDate, LocalDateTime updatedDate, MemberDTO memberDTO, String eventAddress, String eventAddressDetail, LocalDate eventStartDate, LocalDate eventEndDate, String eventIntroduction, String eventMemberCount, int eventViewNumber, int eventLikeNumber, List<EventBoardDTO> eventBoardList) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.eventFileDTOS = eventFileDTOS;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.memberDTO = memberDTO;
        this.eventAddress = eventAddress;
        this.eventAddressDetail = eventAddressDetail;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventIntroduction = eventIntroduction;
        this.eventMemberCount = eventMemberCount;
        this.eventViewNumber = eventViewNumber;
        this.eventLikeNumber = eventLikeNumber;
        this.eventBoardList = eventBoardList;
    }

}

