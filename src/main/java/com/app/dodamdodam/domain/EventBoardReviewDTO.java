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

    @QueryProjection
    public EventBoardReviewDTO(Long id, MemberDTO memberDTO, String replyContent) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.replyContent = replyContent;
    }
}
