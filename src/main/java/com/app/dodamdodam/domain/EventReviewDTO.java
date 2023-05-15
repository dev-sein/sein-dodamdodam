package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
public class EventReviewDTO {
    private Long id;
    private String replyContent;
    private String replyCount;

    @QueryProjection
    public EventReviewDTO(Long id, String replyContent, String replyCount) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyCount = replyCount;
    }
}
