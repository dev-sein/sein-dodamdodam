package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class EventReplyDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String replyContent;
    private Long boardId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public EventReplyDTO(Long id, MemberDTO memberDTO, String replyContent, Long boardId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.replyContent = replyContent;
        this.boardId = boardId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
