package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
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
    private MemberDTO memberDTO;
    private List<EventFileDTO> eventFileDTOS;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private String eventAddress;
    private String eventAddressDetail;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String eventIntroduction;
    private String eventMemberCount;

}
