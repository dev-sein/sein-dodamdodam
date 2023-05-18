package com.app.dodamdodam.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class EventBoardReviewDTO {
    private Long id;
    private String memberName;
    private String replyContent;
    private String gradeTitle;
    private Integer eventReviewCount;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public EventBoardReviewDTO(Long id, String memberName, String replyContent, String gradeTitle, Integer eventReviewCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberName = memberName;
        this.replyContent = replyContent;
        this.gradeTitle = gradeTitle;
        this.eventReviewCount = eventReviewCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
