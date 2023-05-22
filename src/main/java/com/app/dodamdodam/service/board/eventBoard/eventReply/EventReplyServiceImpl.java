package com.app.dodamdodam.service.board.eventBoard.eventReply;

import com.app.dodamdodam.domain.EventReplyDTO;
import com.app.dodamdodam.entity.event.EventBoard;
import com.app.dodamdodam.entity.event.EventReply;
import com.app.dodamdodam.repository.board.event.board.EventBoardRepository;
import com.app.dodamdodam.repository.board.event.reply.EventReplyRepository;
import com.app.dodamdodam.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("eventBoard")
public class EventReplyServiceImpl implements EventReplyService {
    @Autowired
    private EventBoardRepository eventBoardRepository;

    @Autowired
    private EventReplyRepository eventReplyRepository;

    @Autowired
    private MemberRepository memberRepository;

    /* 이벤트 게시글 댓글 작성 */
    @Override
    public void saveEventBoardReply(EventReply eventReply, Long boardId, Long memberId) {
        eventReply.setEventBoard(eventBoardRepository.findById(boardId).get());
        eventReply.setMember(memberRepository.findById(memberId).get());
        eventReplyRepository.save(eventReply);
    }

    /* 이벤트 게시글 댓글 수정 */
    @Override
    public void setEventReplyContent(EventReply updatedEventReply, Long replyId) {
        eventReplyRepository.findById(replyId).ifPresent(eventReply -> eventReply.setEventReplyContent(updatedEventReply.getReplyContent()));
    }

    /* 이벤트 게시글 댓글 삭제 */
    @Override
    public void removeEventReply(Long replyId) {
        eventReplyRepository.findById(replyId).ifPresent(eventReply -> eventReplyRepository.delete(eventReply));
    }

    /* 이벤트 게시글 boardId로 댓글 리스트 뿌려주기 */
    @Override
    public List<EventReplyDTO> getEventRepliesByBoardId(Pageable pageable, Long boardId) {
        Page<EventReply> eventReplies = eventReplyRepository.findEventRepliesByBoardId_QueryDsl(pageable, boardId);
        List<EventReplyDTO> eventReplyDTOS = eventReplies.get().map(eventReply -> toEventReplyDTO(eventReply)).collect(Collectors.toList());
        return eventReplyDTOS;
    }

    /* 이벤트 게시글 boardId로 그 게시글에 달린 댓글 총 개수 구하기 */
    @Override
    public Long getEventRepliesCountByBoardId(Pageable pageable, Long boardId) {
        Page<EventReply> eventReplies = eventReplyRepository.findEventRepliesByBoardId_QueryDsl(pageable, boardId);
        return eventReplies.getTotalElements();
    }

    /* 댓글 지운 후 그 board에 남아있는 댓글 수 조회 */
    @Override
    public Integer getEventRepliesCountByReplyId(Long replyId) {
        return eventBoardRepository.findReplyCountByReplyId_QueryDsl(replyId);
    }
}
