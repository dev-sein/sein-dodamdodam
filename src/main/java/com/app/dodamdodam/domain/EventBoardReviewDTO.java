package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventBoardReviewDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String replyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public EventBoardReviewDTO(Long id, MemberDTO memberDTO, String replyContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.replyContent = replyContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
