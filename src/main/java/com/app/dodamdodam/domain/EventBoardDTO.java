package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.type.EventType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class EventBoardDTO {

    /*게시글 정보*/
    private Long id;
    private String boardTitle;
    private String boardContent;

    /* 이벤트 보드 정보 다 받아오기*/
    private Address address;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;

    /* 좋아요 */
    private Integer eventLikeCount;
    private Integer eventReplyCount;

    /*기업작성*/
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;

    /*댓글 정보*/
    private List<EventReplyDTO> eventReplyDTOS;

    /*파일*/
    private List<EventFileDTO> eventFileDTOS;

    /*멤버 정보 */
    private MemberDTO memberDTO;

    /*수락상태*/
    private EventType eventStatus;

    /*만든날짜, 업뎃일*/
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;




    @Builder
    public EventBoardDTO(Long id, String boardTitle, String boardContent, Address address, LocalDate eventStartDate, LocalDate eventEndDate, Integer eventLikeCount, Integer eventReplyCount, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, List<EventReplyDTO> eventReplyDTOS, List<EventFileDTO> eventFileDTOS, MemberDTO memberDTO, EventType eventStatus, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.address = address;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventLikeCount = eventLikeCount;
        this.eventReplyCount = eventReplyCount;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.eventReplyDTOS = eventReplyDTOS;
        this.eventFileDTOS = eventFileDTOS;
        this.memberDTO = memberDTO;
        this.eventStatus = eventStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
