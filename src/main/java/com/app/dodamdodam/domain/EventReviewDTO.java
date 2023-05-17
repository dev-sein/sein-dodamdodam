package com.app.dodamdodam.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
public class EventReviewDTO {
    private Long id;
    private String memberName;
    /*등급별 이미지*/
    private String gradeTitle;

    private String replyContent;
    private Integer eventReviewCount;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public EventReviewDTO(Long id, String memberName, String gradeTitle, String replyContent, Integer eventReviewCount, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberName = memberName;
        this.gradeTitle = gradeTitle;
        this.replyContent = replyContent;
        this.eventReviewCount = eventReviewCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
