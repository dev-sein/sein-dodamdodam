package com.app.dodamdodam.domain;

import com.app.dodamdodam.entity.event.EventReview;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class EventBoardReviewDTO {
    private Long id;
    private String memberName;
    private String replyContent;
    private String gradeTitle;



    public EventReview toEntity(){
        return EventReview.builder()
                .replyContent(replyContent)
                .build();
    }
}
