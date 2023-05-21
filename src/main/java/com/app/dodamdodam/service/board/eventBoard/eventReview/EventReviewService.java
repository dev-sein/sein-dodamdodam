package com.app.dodamdodam.service.board.eventBoard.eventReview;

import com.app.dodamdodam.domain.EventReviewDTO;
import com.app.dodamdodam.entity.reply.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventReviewService {
    public Slice<EventReviewDTO> findAllByEventReviewId(Pageable pageable, Long id, Boolean isOrderByDate);

    public EventReviewDTO saveEventReview(EventReviewDTO eventReviewDTO, Long eventReviewId, Long memberId);

    default <T extends Reply>  EventReviewDTO eventReviewToDTO(T reply) {
        return EventReviewDTO.builder()
                .id(reply.getId())
                .replyContent(reply.getReplyContent())
                .createdDate(reply.getCreatedDate())
                .updatedDate(reply.getUpdatedDate())
                .memberName(reply.getMember().getMemberName())
                .build();
    }

    // To Entity 메서드, @Overide 하여 재정의 할 것. eventReviewId는 댓글이 달린 테이블
    public <T extends Reply> T replyToEntity(EventReviewDTO eventReviewDTO, Long eventReviewId, Long memberId);
}
