package com.app.dodamdodam.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
public class EventBoardReplyDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private MemberDTO memberDTO;
    private List<EventReplyDTO> eventReplyDTOS;

    @Builder
    public EventBoardReplyDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, List<EventReplyDTO> eventReplyDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.eventReplyDTOS = eventReplyDTOS;
    }
}
