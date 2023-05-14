package com.app.dodamdodam.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EventReviewDTO {
    private Long id;
    private String replyContent;
    private String replyCount;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public EventReviewDTO(Long id, String replyContent, String replyCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.replyContent = replyContent;
        this.replyCount = replyCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
