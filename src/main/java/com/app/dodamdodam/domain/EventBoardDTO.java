package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.embeddable.Address;
import com.app.dodamdodam.type.EventType;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class EventBoardDTO {

    /* 이벤트 보드 정보 다 받아오기*/
    private Long id;
    private Address address;
    private String eventStartDate;
    private String eventEndDate;
    private String eventIntroduction;

    /*게시글 정보*/
    private String boardTitle;
    private String boardContent;

    /* 좋아요 */
    private Integer likeCount;
    private Integer replyCount;

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
    List<EventReplyDTO> reviews;
    /*파일*/
    private List<FileDTO> fileDTOS;


    /*만든날짜, 업뎃일*/
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public EventBoardDTO() {
        this.fileDTOS = new ArrayList<>();
    }

    @Builder
    public EventBoardDTO(Long id, Address address, String eventStartDate, String eventEndDate, String eventIntroduction, String boardTitle, String boardContent, Integer likeCount, Integer replyCount, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail, MemberDTO memberDTO, List<FileDTO> fileDTOS, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.address = address;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventIntroduction = eventIntroduction;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.eventStatus = eventStatus;
        this.eventBusinessNumber = eventBusinessNumber;
        this.eventBusinessName = eventBusinessName;
        this.eventBusinessTel = eventBusinessTel;
        this.eventBusinessEmail = eventBusinessEmail;
        this.memberDTO = memberDTO;
        this.fileDTOS = fileDTOS;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
