package com.app.dodamdodam.service.board.eventBoard.eventReview;

import com.app.dodamdodam.domain.EventReviewDTO;
import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.entity.reply.Reply;
import com.app.dodamdodam.repository.board.event.reply.EventReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("eventBoard")
public class EventReviewServiceImpl implements EventReviewService {
    private final EventReplyRepository eventReviewRepository;

    @Override
    public Slice<EventReviewDTO> findAllByEventReviewId(Pageable pageable, Long id, Boolean isOrderByDate) {
        // 최신순
        Slice<EventReply> eventReviewSlice = null;
        isOrderByDate = isOrderByDate == null ? true : isOrderByDate;

        if(isOrderByDate) {
            eventReviewSlice = eventReviewRepository.findAllByEventReview(pageable, id);
        }

        List<EventReviewDTO> eventReviewDTOList = eventReviewSlice
                .get()
                .map(this::eventReviewToDTO)
                .collect(Collectors.toList());
        return new SliceImpl<>(eventReviewDTOList, pageable, eventReviewSlice.hasNext());
    }

    @Override
    public EventReviewDTO saveEventReview(EventReviewDTO eventReviewDTO, Long eventReviewId, Long memberId) {
        return null;
    }

    @Override
    public <T extends Reply> T replyToEntity(EventReviewDTO eventReviewDTO, Long eventReviewId, Long memberId) {
        return null;
    }
}
