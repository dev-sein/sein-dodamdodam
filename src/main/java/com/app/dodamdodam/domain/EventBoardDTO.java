package com.app.dodamdodam.domain;

import com.app.dodamdodam.type.EventType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EventBoardDTO {

    /* 이벤트 보드 정보 다 받아오기*/
    private Long id;
    private String eventAddress;
    private String eventAddressDetail;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String eventIntroduction;

    /* 좋아요 */
    private int eventLikeNumber;
    /*수락상태*/
    private EventType eventStatus;

    /*기업작성*/
    private String eventBusinessNumber;
    private String eventBusinessName;
    private String eventBusinessTel;
    private String eventBusinessEmail;

    @QueryProjection
    public EventBoardDTO(Long id, String eventAddress, String eventAddressDetail, LocalDate eventStartDate, LocalDate eventEndDate, String eventIntroduction, int eventLikeNumber, EventType eventStatus, String eventBusinessNumber, String eventBusinessName, String eventBusinessTel, String eventBusinessEmail) {
        this.id = id;
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
    }
}
